package com.yanchelenko.piggybank.modules.base.ui_model.models

import androidx.compose.runtime.Immutable
import com.yanchelenko.piggybank.modules.base.ui_model.models.StableInstant.Companion.DISTANT_PAST
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Immutable
@Serializable
data class ProductUiModel(
    val productId: Long = 0L,
    val barcode: String,
    val productName: String = "",
    val weight: Double = 0.0,
    val price: Double = 0.0,
    val pricePerKg: Double = 0.0,

    @Contextual
    val addedAt: StableInstant = DISTANT_PAST
)
