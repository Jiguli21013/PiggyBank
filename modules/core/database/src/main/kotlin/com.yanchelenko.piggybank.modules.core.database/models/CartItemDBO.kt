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
            entity = ProductDBO::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.SET_NULL // если товар удалили, строка в корзине остаётся
        )
    ],
    indices = [
        Index(value = ["cartId"]), // часто фильтруем по cartId
        Index(value = ["productId"]), // join'ы с продуктами
        Index(value = ["barcode"]) // поиск по штрихкоду
    ]
)

data class CartItemDBO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("cartId") val cartId: Long,
    @ColumnInfo("productId") val productId: Long?,
    @ColumnInfo("barcode") val barcode: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("unitPrice") val unitPrice: Double,
    @ColumnInfo("isWeightBased") val isWeightBased: Boolean,
    @ColumnInfo("weightGrams") val weightGrams: Int?,
    @ColumnInfo("quantity") val quantity: Int,
    @ColumnInfo("createdAtEpochMs") val createdAtEpochMs: Long //todo переименовать AddedAt
)
