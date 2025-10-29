package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.state

sealed interface HistoryOfCartsEffect {
    data object NavigateToCartDetails : HistoryOfCartsEffect

    data class ShowMessage(val message: String) : HistoryOfCartsEffect
    data class ShowError(val message: String) : HistoryOfCartsEffect
}
