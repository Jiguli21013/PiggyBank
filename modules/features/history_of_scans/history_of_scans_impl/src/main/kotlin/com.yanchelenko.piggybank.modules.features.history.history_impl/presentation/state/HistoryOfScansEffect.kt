package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

sealed interface HistoryOfScansEffect {
    data class NavigateToDetails(val product: ScannedProductUiModel) : HistoryOfScansEffect
    data class NavigateToDialogDeleteProduct(val product: ScannedProductUiModel) : HistoryOfScansEffect
    data class ShowMessage(val message: String) : HistoryOfScansEffect
    data class ShowError(val message: String) : HistoryOfScansEffect
}
