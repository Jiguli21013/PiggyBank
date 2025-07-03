package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel

sealed interface HistoryEvent {
    data class OnProductClicked(val product: ProductUiModel) : HistoryEvent
    data class OnProductDeleteClicked(val product: ProductUiModel) : HistoryEvent

    data class OnProductDeleted(val product: ProductUiModel) : HistoryEvent
    data object OnDeleteDialogDismissed : HistoryEvent
}
