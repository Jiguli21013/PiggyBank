package com.yanchelenko.piggybank.modules.core.core_api.models

import kotlinx.datetime.Instant

data class ProductVersion(
    val id: Long,
    val productId: Long,
    val weightGrams: Int,
    val price: Double,
    val pricePerKg: Double,
    val createdAt: Instant,
    val isCurrent: Boolean,
)
