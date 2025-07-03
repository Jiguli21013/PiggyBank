package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state

sealed class EditProductEffect {
    data object NavigateBack : EditProductEffect()
    data class ShowMessage(val message: String) : EditProductEffect()
}
