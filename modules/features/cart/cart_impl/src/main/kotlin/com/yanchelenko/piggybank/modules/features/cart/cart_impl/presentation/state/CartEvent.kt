package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel

sealed interface CartEvent {
    data class OnProductOfCartClicked(val productOfCart: ProductOfCartUiModel) : CartEvent
    data class OnProductOfCartDeleteClicked(val productOfCart: ProductOfCartUiModel) : CartEvent

    data class OnProductOfCartDeleted(val productOfCart: ProductOfCartUiModel) : CartEvent

    data object OnCloseCartClicked : CartEvent
    data object OnDeleteDialogDismissed : CartEvent

    // Internal events for cart-closing flow
    data object CartCloseSucceeded : CartEvent
    data class CartCloseFailed(val message: String) : CartEvent
}
