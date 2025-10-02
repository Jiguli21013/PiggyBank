package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel

sealed interface HistoryEffect {
    data class NavigateToDetails(val product: ProductUiModel) : HistoryEffect
    data class NavigateToDialogDeleteProduct(val product: ProductUiModel) : HistoryEffect
    data class ShowMessage(val message: String) : HistoryEffect
    data class ShowError(val message: String) : HistoryEffect
}
