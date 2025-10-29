package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation

import kotlinx.coroutines.flow.collectLatest
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.getData
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductFromCartUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetActiveCartTotalsUseCase
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.domain.CloseCartUseCaseImpl
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.domain.GetPagedProductsOfCartUseCaseImpl
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.mappers.toCartScreenState
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.mappers.toUiPagingData
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEffect
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEvent
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getPagedProductsOfCartUseCase: GetPagedProductsOfCartUseCaseImpl,
    private val getActiveCartTotalsUseCase: GetActiveCartTotalsUseCase,
    private val closeCartUseCase: CloseCartUseCaseImpl,
    private val deleteProductOfCartUseCase: DeleteProductFromCartUseCase,
    private val logger: Logger
) : BaseViewModel<CartEvent, CommonUiState<CartScreenState>, CartEffect>(
    initialState = CommonUiState.Initializing
) {

    init { observeActiveCartTotals() }

    /**
     * Subscribes to live totals of the active cart and updates the screen state.
     * MVI rule: state is updated only here, VM doesn't calculate totals itself.
     */
    private fun observeActiveCartTotals() {
        viewModelScope.launch(Dispatchers.IO) { //todo io ?
            getActiveCartTotalsUseCase()
                .map { it.toCartScreenState() }
                .collectLatest { cartScreenState ->
                    logger.d(LOG_TAG, "Active cart totals updated: $cartScreenState")
                    setState {
                        when (this) {
                            is CommonUiState.Success -> CommonUiState.Success(cartScreenState)
                            else -> CommonUiState.Success(cartScreenState)
                        }
                    }
                }
        }
    }

    fun pagedItems(): Flow<PagingData<ProductOfCartUiModel>> {
        logger.d(LOG_TAG, "invoke() called — starting to collect paged products")
        return getPagedProductsOfCartUseCase()
            .map { it.toUiPagingData() }
            .cachedIn(scope = viewModelScope)
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates, itemCount: Int) {
        logger.d(
            LOG_TAG,
            "LoadState changed: refresh=${loadState.refresh::class.simpleName}, itemCount=$itemCount"
        )
        if (loadState.refresh is LoadState.NotLoading && itemCount > NO_ITEMS_COUNT) {
            logger.d(LOG_TAG, "Switching to Success state")
            setState {
                when (this) {
                    is CommonUiState.Success -> this
                    else -> CommonUiState.Success(CartScreenState()) //todo не оч уверен что это красиво и норм
                }
            }
        }
    }

    override fun onEvent(event: CartEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")

        when (event) {
            is CartEvent.OnProductOfCartClicked -> {
                //logger.d(LOG_TAG, "Navigate to product details: id=${event.productOfCart.productId}")
                //sendEffect { CartEffect.NavigateToProductOfCartDetails(productOfCart = event.productOfCart) }
            }
            is CartEvent.OnProductOfCartDeleteClicked -> {
                logger.d(LOG_TAG, "Navigate dialog to delete product: cartItemId=${event.productOfCart.cartItemId}")
                sendEffect { CartEffect.NavigateToDialogDeleteProductOfCart(productOfCart = event.productOfCart) }
            }
            is CartEvent.OnProductOfCartDeleted -> {
                logger.d(LOG_TAG, "Delete product requested: cartItemId=${event.productOfCart.cartItemId}")
                deleteProductOfCart(productOfCartId = event.productOfCart.cartItemId)
            }
            is CartEvent.OnCloseCartClicked -> {
                logger.d(LOG_TAG, "Close cart clicked")
                //todo dialog yes no ?
                closeCart()
            }
            is CartEvent.OnDeleteDialogDismissed -> {
                logger.d(LOG_TAG, "Delete product dismissed")
            }
            is CartEvent.CartCloseSucceeded -> {
                logger.d(LOG_TAG, "Cart was closed successfully [event]")
                setState {
                    //todo подумать как сделать красивей
                    when (this) {
                        is CommonUiState.Success -> CommonUiState.Success(data.copy(isCartClosed = true))
                        else -> CommonUiState.Success(CartScreenState(isCartClosed = true))
                    }
                }
                sendEffect { CartEffect.ShowMessage("Корзина закрыта") }
                sendEffect { CartEffect.NavigateToHistoryOfCarts }
            }
            is CartEvent.CartCloseFailed -> {
                logger.e(LOG_TAG, "Close cart failed: ${event.message}")
                sendEffect { CartEffect.ShowMessage(event.message) }
            }
        }
    }

    private fun deleteProductOfCart(productOfCartId: Long) = viewModelScope.launch(Dispatchers.IO) {
        logger.d(LOG_TAG, "Attempting to delete from ACTIVE cart: cartItemId=$productOfCartId")

        when (deleteProductOfCartUseCase(productOfCartId)) {
            is RequestResult.Success -> {
                logger.d(LOG_TAG, "Deleted cart item id=$productOfCartId")
                sendEffect { CartEffect.ShowMessage("Товар удалён из корзины") }//todo
            }
            is RequestResult.Error -> {
                logger.e(LOG_TAG, "Error deleting cart item id=$productOfCartId")
                sendEffect { CartEffect.ShowMessage("Ошибка при удалении товара") }//todo
            }
            is RequestResult.InProgress -> {
                logger.d(LOG_TAG, "Deleting in progress...")
                setState { CommonUiState.Loading }
            }
        }
    }

    private fun closeCart() = viewModelScope.launch(Dispatchers.IO) {
        logger.d(LOG_TAG, "Attempting to close active cart…")

        state.getData {
            val totalItems = it.itemsCount
            val totalPrice = it.totalPrice

            val result = closeCartUseCase(
                totalItems = totalItems,
                totalPrice = totalPrice
            )

            result.onSuccess { closed ->
                if (closed) {
                    onEvent(CartEvent.CartCloseSucceeded)
                }
            }.onFailure { e ->
                onEvent(CartEvent.CartCloseFailed(e.message ?: "Не удалось закрыть корзину"))
            }
        }
    }

    private companion object {
        const val NO_ITEMS_COUNT = 0
        const val LOG_TAG = "CartVM"
    }
}
