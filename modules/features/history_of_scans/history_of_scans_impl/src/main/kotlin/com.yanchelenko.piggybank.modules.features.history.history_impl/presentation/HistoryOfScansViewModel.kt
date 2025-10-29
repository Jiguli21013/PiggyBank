package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yanchelenko.piggybank.modules.features.history.history_impl.domain.usecase.GetPagedScannedProductsUseCaseImpl
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers.toUiPagingData
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers.withDateHeaders
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryOfScansEffect
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryOfScansEvent
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toDomain
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteScannedProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryOfScansViewModel @Inject constructor(
    private val getPagedProductsUseCase: GetPagedScannedProductsUseCaseImpl,
    private val deleteScannedProductUseCase: DeleteScannedProductUseCase,
    private val logger: Logger
) : BaseViewModel<HistoryOfScansEvent, CommonUiState<Unit>, HistoryOfScansEffect>(
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
        when {
            loadState.refresh is LoadState.Loading -> {
                setState { CommonUiState.Loading }
            }

            loadState.refresh is LoadState.NotLoading && (itemCount > NO_ITEMS_COUNT || itemCount == NO_ITEMS_COUNT) -> {
                logger.d(LOG_TAG, "Switching to Success state")
                setState { CommonUiState.Success(Unit) }
            }

            loadState.refresh is LoadState.Error -> {
                val error = (loadState.refresh as LoadState.Error).error
                logger.e(LOG_TAG, "Error while loading items: ${error.message}")
                setState { CommonUiState.Error(error.message ?: "Ошибка загрузки данных") } //todo
            }
        }
    }

    override fun onEvent(event: HistoryOfScansEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")

        when (event) {
            is HistoryOfScansEvent.OnProductClicked -> {
                logger.d(LOG_TAG, "Navigate to product details: id=${event.product.productId}")
                sendEffect { HistoryOfScansEffect.NavigateToDetails(product = event.product) }
            }
            is HistoryOfScansEvent.OnProductDeleteClicked -> {
                logger.d(LOG_TAG, "Navigate dialog to delete product: id=${event.product.productId}")
                sendEffect { HistoryOfScansEffect.NavigateToDialogDeleteProduct(product = event.product) }
            }
            is HistoryOfScansEvent.OnProductDeleted -> {
                logger.d(LOG_TAG, "Delete product requested: id=${event.product.productId}")
                deleteProduct(product = event.product)
            }
            is HistoryOfScansEvent.OnDeleteDialogDismissed -> {
                logger.d(LOG_TAG, "Delete product dismissed")
            }
        }
    }

    private fun deleteProduct(product: ScannedProductUiModel) = viewModelScope.launch {
        logger.d(LOG_TAG, "Attempting to delete product from DB: $product")

        when (val result = deleteScannedProductUseCase(product.toDomain())) {
            is RequestResult.Success -> {
                logger.d(LOG_TAG, "ScannedProduct deleted successfully: id=${product.productId}")
                sendEffect { HistoryOfScansEffect.ShowMessage("Продукт удалён") } //todo from res
            }

            is RequestResult.Error -> {
                logger.e(LOG_TAG, "Error deleting product: ${result.error?.message}")
                sendEffect { HistoryOfScansEffect.ShowError("Ошибка при удалении") } //todo
            }

            is RequestResult.InProgress -> {
                logger.d(LOG_TAG, "Deletion in progress")
            }
        }
    }

    private companion object {
        const val NO_ITEMS_COUNT = 0
        const val LOG_TAG = "HistoryOfScansVM"
    }
}
