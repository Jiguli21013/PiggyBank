package com.yanchelenko.piggybank.modules.core.core_api.models

data class CartTotals(
    val itemsCount: Int,
    val totalWeightGrams: Int,
    val totalPrice: Double
)
