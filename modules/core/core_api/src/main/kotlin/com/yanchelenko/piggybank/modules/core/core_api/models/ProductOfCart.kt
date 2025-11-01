package com.yanchelenko.piggybank.modules.core.core_api.models

data class ProductOfCart(
    val cartItemId: Long,
    val productId: Long?, // для связки с scanned product
    val barcode: String,
    val name: String,
    val unitPrice: Double,
    val isWeightBased: Boolean,
    val weightGrams: Int?,
    val quantity: Int
)
