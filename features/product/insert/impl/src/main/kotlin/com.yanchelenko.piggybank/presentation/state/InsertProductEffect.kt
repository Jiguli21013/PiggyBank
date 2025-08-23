package com.yanchelenko.piggybank.presentation.state

sealed class InsertProductEffect {
    data object NavigateBackToScanner : InsertProductEffect()
    data class ShowMessage(val message: String) : InsertProductEffect()
}
