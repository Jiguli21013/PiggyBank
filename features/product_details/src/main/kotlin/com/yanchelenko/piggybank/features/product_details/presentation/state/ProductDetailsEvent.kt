package com.yanchelenko.piggybank.features.product_details.presentation.state

sealed interface ProductDetailsEvent {
    object OnEditClicked : ProductDetailsEvent
    object OnDeleteClicked : ProductDetailsEvent
    object ConfirmedDelete : ProductDetailsEvent
    object CancelDelete : ProductDetailsEvent
}
