package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

sealed interface HistoryOfScansEvent {
    data class OnProductClicked(val product: ScannedProductUiModel) : HistoryOfScansEvent
    data class OnProductDeleteClicked(val product: ScannedProductUiModel) : HistoryOfScansEvent

    data class OnProductDeleted(val product: ScannedProductUiModel) : HistoryOfScansEvent
    data object OnDeleteDialogDismissed : HistoryOfScansEvent
}
