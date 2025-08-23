package com.yanchelenko.piggybank.presentation.state

import com.yanchelenko.piggybank.models.Product

sealed interface ProductDetailsEvent {
    data object OnEditClicked : ProductDetailsEvent
    data object OnDeleteClicked : ProductDetailsEvent

    data object DialogConfirmedDelete : ProductDetailsEvent
    data object DialogCancelDelete : ProductDetailsEvent

    data class LoadProductByProductId(val productId: Long) : ProductDetailsEvent
    data class ProductFoundInDB(val product: Product) : ProductDetailsEvent
}
