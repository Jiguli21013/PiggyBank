package com.yanchelenko.piggybank.modules.core.core_api.models

import kotlinx.datetime.Instant

public data class ScannedProduct(
    val id: Long,
    val barcode: String,
    val productName: String,
    val weight: Int,
    val price: Double,
    val pricePerKg: Double,
    val addedAt: Instant
)
