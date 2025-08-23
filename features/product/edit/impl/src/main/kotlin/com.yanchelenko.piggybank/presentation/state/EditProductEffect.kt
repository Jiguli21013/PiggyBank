package com.yanchelenko.piggybank.presentation.state

sealed class EditProductEffect {
    data object NavigateBack : EditProductEffect()
    data class ShowMessage(val message: String) : EditProductEffect()
}
