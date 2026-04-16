package com.yanchelenko.piggybank.modules.base.ui_model.models

/**
 * UI representation of a product currently in the cart.
 *
 * @property formattedPrice — displayed price (either per kg or per unit)
 * @property formattedPricePerKg — visible only if weight affects pricing
 * @property weightText — formatted weight (e.g. "150 г" or "—")
 * @property totalPriceText — total cost = unitPrice * quantity (or by weight)
 */
data class ProductOfCartUiModel(
    val cartProductId: Long, // id from cart item
    val scannedProductId: Long?, // nullable: linked product may be deleted later
    val name: String,
    val barcode: String,
    val formattedPrice: String,
    val formattedPricePerKg: String?,
    val quantityText: String,
    val weightText: String?,
    val unitPrice: String,
    val totalPriceText: String,
)
