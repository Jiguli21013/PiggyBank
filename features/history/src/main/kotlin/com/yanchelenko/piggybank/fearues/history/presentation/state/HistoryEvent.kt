package com.yanchelenko.piggybank.fearues.history.presentation.state

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel

sealed interface HistoryEvent {
    data class OnProductClicked(val product: ProductUiModel) : HistoryEvent
    data class OnProductDeleted(val product: ProductUiModel) : HistoryEvent
}
