package com.yanchelenko.piggybank.features.product_edit.presentation.state

sealed class EditProductEffect {
    data object NavigateBack : EditProductEffect()
    data class ShowMessage(val message: String) : EditProductEffect()
}
