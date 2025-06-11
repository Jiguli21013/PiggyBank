package com.yanchelenko.piggybank.fearues.history.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import com.yanchelenko.piggybank.common.mappers.toDomain
import com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase
import com.yanchelenko.piggybank.fearues.history.domain.GetPagedProductsUseCase
import com.yanchelenko.piggybank.fearues.history.presentation.mappers.toUiPagingData
import com.yanchelenko.piggybank.fearues.history.presentation.mappers.withDateHeaders
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryState
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getPagedProductsUseCase: GetPagedProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : BaseViewModel<HistoryEvent, HistoryState, HistoryEffect>(
    initialState = HistoryState.None
) {
    val items = getPagedProductsUseCase()
        .map { product ->
            product
                .toUiPagingData()
                .withDateHeaders()
        }
        .cachedIn(scope = viewModelScope)

    fun onLoadStateChanged(loadState: CombinedLoadStates, itemCount: Int) {
        if (loadState.refresh is LoadState.NotLoading && itemCount > 0) {
            setState { HistoryState.Success }
        }
    }

    override fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnProductClicked -> {
                sendEffect { HistoryEffect.NavigateToDetails(product = event.product) }
            }

            is HistoryEvent.OnProductDeleted -> {
                deleteProduct(product = event.product)
            }
        }
    }

    private fun deleteProduct(product: ProductUiModel) {
        viewModelScope.launch {
            when (deleteProductUseCase(product.toDomain())) {
                is RequestResult.Success -> {
                    //sendEffect { HistoryEffect.ShowMessage("Продукт удалён") }
                }
                is RequestResult.Error -> {
                    sendEffect { HistoryEffect.ShowError("Ошибка при удалении") }
                }
                is RequestResult.InProgress -> { /* можно показать лоадер */ }
            }
        }
    }
}
