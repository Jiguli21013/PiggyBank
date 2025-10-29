package com.yanchelenko.piggybank.modules.base.ui_model.models

data class ProductOfCartUiModel(
    val cartItemId: Long, // id from cart item
    val productId: Long, // id from scanned product
    val name: String,
    val barcode: String,
    val formattedPrice: String,
    val formattedPricePerKg: String?,
    val quantityText: String,
    val weightText: String?,
    val unitPrice: String,
    val totalPriceText: String,
)
