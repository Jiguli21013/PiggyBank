package com.yanchelenko.piggybank.presentation.state

sealed interface ProductDetailsEffect {
    data class NavigateToEdit(val productId: Long) : ProductDetailsEffect
    data object ShowDeleteDialog : ProductDetailsEffect
    data object CloseDeleteDialog : ProductDetailsEffect
    data object DeletionAnimation : ProductDetailsEffect
    data object NavigateBack : ProductDetailsEffect
}
