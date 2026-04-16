package com.yanchelenko.piggybank.modules.core.database.models

import androidx.room.ColumnInfo

data class ProductWithCurrentVersionProjection(
    @ColumnInfo(name = "product_id")
    val productId: Long,

    @ColumnInfo(name = "product_barcode")
    val barcode: String,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "product_created_at_epoch_ms")
    val productCreatedAtEpochMs: Long,

    @ColumnInfo(name = "version_id")
    val versionId: Long,

    @ColumnInfo(name = "version_product_id")
    val versionProductId: Long,

    @ColumnInfo(name = "version_weight_grams")
    val weightGrams: Int,

    @ColumnInfo(name = "version_price")
    val price: Double,

    @ColumnInfo(name = "version_price_per_kg")
    val pricePerKg: Double,

    @ColumnInfo(name = "version_created_at_epoch_ms")
    val versionCreatedAtEpochMs: Long,

    @ColumnInfo(name = "version_is_current")
    val isCurrent: Boolean,
)
