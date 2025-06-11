package com.yanchelenko.piggybank.fearues.history.presentation.state

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel

sealed interface HistoryEffect {
    data class NavigateToDetails(val product: ProductUiModel) : HistoryEffect
    data class ShowError(val message: String) : HistoryEffect
}
