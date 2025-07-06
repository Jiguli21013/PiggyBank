package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yanchelenko.piggybank.modules.features.history.history_impl.domain.usecase.GetPagedProductsUseCaseImpl
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers.toUiPagingData
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers.withDateHeaders
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryEffect
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryEvent
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.ui_model.mapper.toDomain
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getPagedProductsUseCase: GetPagedProductsUseCaseImpl,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val logger: Logger
) : BaseViewModel<HistoryEvent, CommonUiState<Unit>, HistoryEffect>(
    initialState = CommonUiState.Initializing
) {

    fun pagedItems(): Flow<PagingData<ListItem>> {
        logger.d(LOG_TAG,"invoke() called — starting to collect paged products")
        return getPagedProductsUseCase()
            .map { it.toUiPagingData().withDateHeaders() }
            .cachedIn(scope = viewModelScope)
    }


    fun onLoadStateChanged(loadState: CombinedLoadStates, itemCount: Int) {
        logger.d(
            LOG_TAG,
            "LoadState changed: refresh=${loadState.refresh::class.simpleName}, itemCount=$itemCount"
        )
        if (loadState.refresh is LoadState.NotLoading && itemCount > NO_ITEMS_COUNT) {
            logger.d(LOG_TAG, "Switching to Success state")
            setState { CommonUiState.Success(Unit) }
        }
    }

    override fun onEvent(event: HistoryEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")

        when (event) {
            is HistoryEvent.OnProductClicked -> {
                logger.d(LOG_TAG, "Navigate to product details: id=${event.product.productId}")
                sendEffect { HistoryEffect.NavigateToDetails(product = event.product) }
            }
            is HistoryEvent.OnProductDeleteClicked -> {
                logger.d(LOG_TAG, "Navigate dialog to delete product: id=${event.product.productId}")
                sendEffect { HistoryEffect.NavigateToDialogDeleteProduct(product = event.product) }
            }
            is HistoryEvent.OnProductDeleted -> {
                logger.d(LOG_TAG, "Delete product requested: id=${event.product.productId}")
                deleteProduct(product = event.product)
            }
            is HistoryEvent.OnDeleteDialogDismissed -> {
                logger.d(LOG_TAG, "Delete product dismissed")
            }
        }
    }

    private fun deleteProduct(product: ProductUiModel) = viewModelScope.launch {
            logger.d(LOG_TAG, "Attempting to delete product from DB: $product")

            when (val result = deleteProductUseCase(product.toDomain())) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "Product deleted successfully: id=${product.productId}")
                    sendEffect { HistoryEffect.ShowMessage("Продукт удалён") } //todo from res
                }

                is RequestResult.Error -> {
                    logger.e(LOG_TAG, "Error deleting product: ${result.error?.message}")
                    sendEffect { HistoryEffect.ShowError("Ошибка при удалении") } //todo
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Deletion in progress")
                }
            }
        }

    private companion object {
        const val NO_ITEMS_COUNT = 0
        const val LOG_TAG = "HistoryVM"
    }
}
