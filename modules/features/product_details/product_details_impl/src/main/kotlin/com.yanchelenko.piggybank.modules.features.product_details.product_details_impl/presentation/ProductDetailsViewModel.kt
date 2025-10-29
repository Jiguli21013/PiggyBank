package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsEffect
import com.yanchelenko.piggybank.modules.core.core_api.domain.mapper.toUserMessage
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsEvent
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.getData
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toDomain
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toUi
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteScannedProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.BaseDomainException
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductByProductIdUseCase: GetProductByIdUseCase,
    private val deleteScannedProductUseCase: DeleteScannedProductUseCase,
    private val logger: Logger,
) : BaseViewModel<ProductDetailsEvent, CommonUiState<ScannedProductUiModel>, ProductDetailsEffect>(
    initialState = CommonUiState.Initializing,
) {
    // Cache the productId for later refreshes
    private val productId: Long

    init {
        productId = savedStateHandle.get<Long>(AppDestination.ProductDetailsDestination.Meta.arguments.first().name)
            ?: error("productId is missing in SavedStateHandle")
        logger.d(LOG_TAG, "Init with productId=$productId")
        onEvent(event = ProductDetailsEvent.LoadProductByProductId(productId = productId))
    }

    /**
     * Explicit refresh API for the screen: call this from the UI when the screen returns to foreground.
     * This guarantees fresh data even if this VM was retained on the back stack.
     */
    fun refresh() {
        logger.d(LOG_TAG, "Manual refresh for productId=$productId")
        onEvent(ProductDetailsEvent.LoadProductByProductId(productId = productId))
    }

    override fun onEvent(event: ProductDetailsEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")
        when (event) {
            is ProductDetailsEvent.OnEditClicked -> {
                uiState.value.getData { product ->
                    logger.d(LOG_TAG, "Edit requested for productId=${product.productId}")
                    sendEffect { ProductDetailsEffect.NavigateToEdit(productId = product.productId) }
                }
            }
            is ProductDetailsEvent.OnDeleteClicked -> {
                logger.d(LOG_TAG, "Delete dialog requested")
                sendEffect { ProductDetailsEffect.ShowDeleteDialog }
            }
            is ProductDetailsEvent.DialogCancelDelete -> {
                logger.d(LOG_TAG, "Delete canceled")
                sendEffect { ProductDetailsEffect.CloseDeleteDialog }
            }
            is ProductDetailsEvent.DialogConfirmedDelete -> {
                uiState.value.getData { product ->
                    logger.d(LOG_TAG, "Confirmed delete for productId=${product.productId}")
                    sendEffect { ProductDetailsEffect.CloseDeleteDialog }
                    deleteProductFromDB()
                }
            }
            is ProductDetailsEvent.LoadProductByProductId -> {
                logger.d(LOG_TAG, "Loading product by ID: ${event.productId}")
                loadProductByProductId(productId = event.productId)
            }

            is ProductDetailsEvent.ProductFoundInDB -> {
                logger.d(LOG_TAG, "ScannedProduct loaded from DB: ${event.product}")
                setState { CommonUiState.Success(data = event.product) }
            }
        }
    }

    private fun loadProductByProductId(productId: Long) {
        logger.d(LOG_TAG, "Start DB lookup for productId=$productId")
        viewModelScope.launch {
            when (val result = getProductByProductIdUseCase(productId)) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "ScannedProduct loaded: ${result.data}")
                    onEvent(ProductDetailsEvent.ProductFoundInDB(product = result.data.toUi()))
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка" //todo
                    logger.e(LOG_TAG, "Error loading product: $message")
                    //sendEffect { ProductDetailsEffect.ShowMessage("Ошибка загрузки продукта: $message") }//todo to res
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Loading in progress...")
                }
            }
        }
    }

    private fun deleteProductFromDB() {
        uiState.value.getData { product ->
            logger.d(LOG_TAG, "Attempting to delete product: $product")

            viewModelScope.launch {
                when (val result = deleteScannedProductUseCase(product.toDomain())) {

                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "ScannedProduct successfully deleted")
                        sendEffect { ProductDetailsEffect.DeletionAnimation }
                        logger.d(LOG_TAG, "Animation for deletion")
                        delay(timeMillis = DELETION_ANIMATION_DELAY_MS)
                        sendEffect { ProductDetailsEffect.NavigateBack }
                        logger.d(LOG_TAG, "Navigation back")
                    }

                    is RequestResult.Error -> {
                        logger.e(LOG_TAG, "Error deleting product: ${result.error?.message}")
                        //todo
                    }

                    is RequestResult.InProgress -> {
                        logger.d(LOG_TAG, "Delete in progress")
                    }
                }
            }
        }
    }

    private companion object {
        const val LOG_TAG = "ProductDetailsVM"
        const val DELETION_ANIMATION_DELAY_MS = 200L
    }
}
