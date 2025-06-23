package com.yanchelenko.piggybank.fearues.history.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toDomain
import com.yanchelenko.piggybank.common.ui_state.CommonUiState
import com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase
import com.yanchelenko.piggybank.fearues.history.domain.GetPagedProductsUseCase
import com.yanchelenko.piggybank.fearues.history.presentation.components.ListItem
import com.yanchelenko.piggybank.fearues.history.presentation.mappers.toUiPagingData
import com.yanchelenko.piggybank.fearues.history.presentation.mappers.withDateHeaders
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelneko.piggybank.common.core_utils.Logger
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getPagedProductsUseCase: GetPagedProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    logger: Logger
) : BaseViewModel<HistoryEvent, CommonUiState<Unit>, HistoryEffect>(
    logger = logger,
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

            is HistoryEvent.OnProductDeleted -> {
                logger.d(LOG_TAG, "Delete product requested: id=${event.product.productId}")
                deleteProduct(product = event.product)
            }
        }
    }

    private fun deleteProduct(product: ProductUiModel) = viewModelScope.launch {
            logger.d(LOG_TAG, "Attempting to delete product from DB: $product")

            when (val result = deleteProductUseCase(product.toDomain())) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "Product deleted successfully: id=${product.productId}")
                    //sendEffect { HistoryEffect.ShowMessage("Продукт удалён") }
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
