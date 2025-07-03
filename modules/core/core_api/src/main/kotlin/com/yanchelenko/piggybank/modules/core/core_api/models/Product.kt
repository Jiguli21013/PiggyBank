package com.yanchelenko.piggybank.modules.core.core_api.models

import kotlinx.datetime.Instant

public data class Product(
    val id: Long,
    val barcode: String,
    val productName: String,
    val weight: Double,
    val price: Double,
    val pricePerKg: Double,
    val addedAt: Instant
)
