package com.yanchelenko.piggybank.presentation.state

import com.yanchelenko.piggybank.models.Product

sealed interface HistoryEvent {
    data class OnProductClicked(val product: Product) : HistoryEvent
    data class OnProductDeleteClicked(val product: Product) : HistoryEvent

    data class OnProductDeleted(val product: Product) : HistoryEvent
    data object OnDeleteDialogDismissed : HistoryEvent
}
