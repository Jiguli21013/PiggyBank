package com.yanchelenko.piggybank.modules.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart_items",
    foreignKeys = [
        ForeignKey(
            entity = CartDBO::class,
            parentColumns = ["id"],
            childColumns = ["cartId"],
            onDelete = ForeignKey.CASCADE // если корзину удалили — её товары тоже
        ),
        ForeignKey(
            entity = ScannedProductDBO::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.SET_NULL // если товар удалили, строка в корзине остаётся
        )
    ],
    indices = [
        Index(value = ["cartId"]),
        Index(value = ["productId"]),
        Index(value = ["barcode"])
    ]
)
data class CartItemDBO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("cartId") val cartId: Long,
    @ColumnInfo("productId") val productId: Long,
    @ColumnInfo("barcode") val barcode: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("unitPrice") val unitPrice: Double,
    /**
     * true — вес влияет на цену (например, товар по цене за кг),
     * false — вес не влияет, цена фиксирована за упаковку или штуку.
     */
    @ColumnInfo("isWeightImportant") val isWeightImportant: Boolean,
    @ColumnInfo("weightGrams") val weightGrams: Int?,
    @ColumnInfo("quantity") val quantity: Int,
    @ColumnInfo("addedAtEpochMs") val addedAtEpochMs: Long
)
