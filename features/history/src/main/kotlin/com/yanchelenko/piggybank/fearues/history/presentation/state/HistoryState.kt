package com.yanchelenko.piggybank.fearues.history.presentation.state

sealed class HistoryState {
    data object None: HistoryState()
    data object Success : HistoryState()
    data object Loading : HistoryState()
    data class Error(val message: String? = null) : HistoryState()
}
