package com.yanchelenko.piggybank.features.product_insert.presentation.state

sealed class InsertProductEffect {
    data object NavigateBackToScanner : InsertProductEffect()
    data class ShowMessage(val message: String) : InsertProductEffect()
}
