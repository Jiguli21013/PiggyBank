package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state

sealed interface ProductDetailsEvent {
    data object OnEditClicked : ProductDetailsEvent
    data object OnDeleteClicked : ProductDetailsEvent

    data object DialogConfirmedDelete : ProductDetailsEvent
    data object DialogCancelDelete : ProductDetailsEvent

    data class LoadProductByProductId(val productId: Long) : ProductDetailsEvent
    data class ProductFoundInDB(val state: ProductDetailsState) : ProductDetailsEvent
}
