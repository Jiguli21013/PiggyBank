package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.state

sealed interface HistoryOfCartsEvent {
    data object OnCartClicked : HistoryOfCartsEvent
}
