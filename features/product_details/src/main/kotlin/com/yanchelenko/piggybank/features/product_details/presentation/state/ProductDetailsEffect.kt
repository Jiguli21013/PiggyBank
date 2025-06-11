package com.yanchelenko.piggybank.features.product_details.presentation.state

sealed interface ProductDetailsEffect {
    data class NavigateToEdit(val productId: Long) : ProductDetailsEffect
    object ShowDeleteDialog : ProductDetailsEffect
    object CloseDeleteDialog : ProductDetailsEffect
    object NavigateBack : ProductDetailsEffect
}
