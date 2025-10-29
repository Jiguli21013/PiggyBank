package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.domain.GetPagedCartsUseCaseImpl
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.mappers.toUiPagingData
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.mappers.withDateHeaders
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.state.HistoryOfCartsEffect
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.state.HistoryOfCartsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HistoryOfCartsViewModel @Inject constructor(
    private val getPagedCartsUseCase: GetPagedCartsUseCaseImpl,
    private val logger: Logger
) : BaseViewModel<HistoryOfCartsEvent, CommonUiState<Unit>, HistoryOfCartsEffect>(
    initialState = CommonUiState.Initializing
) {

    fun pagedItems(): Flow<PagingData<ListItem>> {
        logger.d(LOG_TAG,"invoke() called — starting to collect paged products")
        return getPagedCartsUseCase()
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

    override fun onEvent(event: HistoryOfCartsEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")

        when (event) {
            is HistoryOfCartsEvent.OnCartClicked -> {
                //logger.d(LOG_TAG, "Navigate to cart details: car id=${event.cart.cartId}")
                //sendEffect { HistoryOfCartsEffect.NavigateToCartDetails(cartId = event.cart.cartId) }
            }
        }
    }

    private companion object {
        const val NO_ITEMS_COUNT = 0
        const val LOG_TAG = "HistoryOfCartsVM"
    }
}
