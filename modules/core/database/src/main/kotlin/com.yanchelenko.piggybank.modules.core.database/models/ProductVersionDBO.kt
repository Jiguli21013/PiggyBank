package com.yanchelenko.piggybank.modules.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_versions",
    foreignKeys = [
        ForeignKey(
            entity = ProductDBO::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [
        Index(value = ["productId"]),
        Index(value = ["productId", "isCurrent"]),
    ]
)
data class ProductVersionDBO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "productId")
    val productId: Long,

    @ColumnInfo(name = "weightGrams")
    val weightGrams: Int,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "pricePerKg")
    val pricePerKg: Double,

    @ColumnInfo(name = "createdAtEpochMs")
    val createdAtEpochMs: Long,

    @ColumnInfo(name = "isCurrent")
    val isCurrent: Boolean,
)
