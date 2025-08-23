package com.yanchelenko.piggybank.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yanchelenko.piggybank.domain.DeleteProductUseCase
import com.yanchelenko.piggybank.logger.Logger
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.mvi.BaseViewModel
import com.yanchelenko.piggybank.mvi.UiState
import com.yanchelenko.piggybank.presentation.mappers.withDateHeaders
import com.yanchelenko.piggybank.presentation.models.ListItem
import com.yanchelenko.piggybank.presentation.state.HistoryEffect
import com.yanchelenko.piggybank.presentation.state.HistoryEvent
import com.yanchelenko.piggybank.result.RequestResult
import com.yanchelenko.piggybank.usecase.GetPagedProductsUseCaseImpl
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
) : BaseViewModel<HistoryEvent, UiState<Unit>, HistoryEffect>(
    initialState = UiState.Loading
) {

    fun pagedItems(): Flow<PagingData<ListItem>> {
        logger.d(LOG_TAG, "invoke() called — starting to collect paged products")
        return getPagedProductsUseCase()
            .map { it.withDateHeaders() }
            .cachedIn(scope = viewModelScope)
    }


    fun onLoadStateChanged(loadState: CombinedLoadStates, itemCount: Int) {
        logger.d(
            LOG_TAG,
            "LoadState changed: refresh=${loadState.refresh::class.simpleName}, itemCount=$itemCount"
        )
        if (loadState.refresh is LoadState.NotLoading && itemCount > NO_ITEMS_COUNT) {
            logger.d(LOG_TAG, "Switching to Success state")
            setState { UiState.Success(Unit) }
        }
    }

    override fun onEvent(event: HistoryEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")

        when (event) {
            is HistoryEvent.OnProductClicked -> {
                logger.d(LOG_TAG, "Navigate to product details: id=${event.product.id}")
                sendEffect { HistoryEffect.NavigateToDetails(product = event.product) }
            }

            is HistoryEvent.OnProductDeleteClicked -> {
                logger.d(LOG_TAG, "Navigate dialog to delete product: id=${event.product.id}")
                sendEffect { HistoryEffect.NavigateToDialogDeleteProduct(product = event.product) }
            }

            is HistoryEvent.OnProductDeleted -> {
                logger.d(LOG_TAG, "Delete product requested: id=${event.product.id}")
                deleteProduct(product = event.product)
            }

            is HistoryEvent.OnDeleteDialogDismissed -> {
                logger.d(LOG_TAG, "Delete product dismissed")
            }
        }
    }

    private fun deleteProduct(product: Product) = viewModelScope.launch {
        logger.d(LOG_TAG, "Attempting to delete product from DB: $product")

        when (val result = deleteProductUseCase(product)) {
            is RequestResult.Success -> {
                logger.d(LOG_TAG, "Product deleted successfully: id=${product.id}")
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
