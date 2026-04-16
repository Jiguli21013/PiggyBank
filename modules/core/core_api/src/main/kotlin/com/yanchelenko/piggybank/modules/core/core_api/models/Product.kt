package com.yanchelenko.piggybank.modules.core.core_api.models

import kotlinx.datetime.Instant

data class Product(
    val id: Long,
    val barcode: String,
    val productName: String,
    val createdAt: Instant,
)
