package com.yanchelenko.piggybank.common.ui_models

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable // не Parcelable чтоб модуль был независим от Android
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
