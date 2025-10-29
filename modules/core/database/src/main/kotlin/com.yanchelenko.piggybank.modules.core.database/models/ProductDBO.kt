package com.yanchelenko.piggybank.modules.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//todo rename to scanned_products
@Entity(tableName = "products")
data class ProductDBO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("barcode") val barcode: String,
    @ColumnInfo("productName") val productName: String,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("price") val price: Double,
    @ColumnInfo("pricePerKg") val pricePerKg: Double,
    @ColumnInfo("addedAt") val addedAt: Long,
    //@ColumnInfo("updatedAt") val updatedAt: Long, todo доделать логику.
)
