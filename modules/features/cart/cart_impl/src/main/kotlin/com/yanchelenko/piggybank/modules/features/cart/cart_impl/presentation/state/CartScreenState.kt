package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state

data class CartScreenState(
    val itemsCount: Int = 0,
    val totalWeightGrams: Int = 0,
    val totalPrice: Double = 0.0,

    val isCartClosed: Boolean = false
)
