package com.yanchelenko.piggybank.modules.core.core_api.models

data class CartTotalsRaw(
    val itemsCount: Int,
    val totalWeightGrams: Int,
    val totalPrice: Double
)
