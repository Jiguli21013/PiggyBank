package com.yanchelenko.piggybank.common.ui_models_android.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class ProductUiModel(
    val productId: Long = 0L,
    val barcode: String,
    val productName: String = "",
    val weight: Double = 0.0,
    val price: Double = 0.0,
    val pricePerKg: Double = 0.0,

    @Contextual
    val addedAt: Instant = Instant.DISTANT_PAST
)
