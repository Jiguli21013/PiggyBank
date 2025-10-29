package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel

interface CartEffect {
    data class NavigateToProductOfCartDetails(val productOfCart: ProductOfCartUiModel) : CartEffect
    data class NavigateToDialogDeleteProductOfCart(val productOfCart: ProductOfCartUiModel) : CartEffect
    data class ShowMessage(val message: String) : CartEffect
    data class ShowError(val message: String) : CartEffect

    data object NavigateToHistoryOfCarts : CartEffect
}
