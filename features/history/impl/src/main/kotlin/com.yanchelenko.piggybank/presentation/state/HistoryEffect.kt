package com.yanchelenko.piggybank.presentation.state

import com.yanchelenko.piggybank.models.Product

sealed interface HistoryEffect {
    data class NavigateToDetails(val product: Product) : HistoryEffect
    data class NavigateToDialogDeleteProduct(val product: Product) : HistoryEffect
    data class ShowMessage(val message: String) : HistoryEffect
    data class ShowError(val message: String) : HistoryEffect
}
