package com.yanchelenko.piggybank.models

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
