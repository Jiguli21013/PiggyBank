package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state

sealed class InsertProductEffect {
    data object NavigateBackToScanner : InsertProductEffect()
    data class ShowMessage(val message: String) : InsertProductEffect()
}
