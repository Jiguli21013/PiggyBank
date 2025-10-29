package com.yanchelenko.piggybank.modules.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(
    tableName = "carts",
    indices = [
        Index(value = ["status"]) // быстрый WHERE status='ACTIVE'
    ]
)

data class CartDBO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Long = 0,
    @ColumnInfo("storeId") val storeId: Long?, // null - пользователь не выбрал магазин
    @ColumnInfo("status") val status: String, // todo "ACTIVE" | "CLOSED" ?
    @ColumnInfo("totalItems") val totalItems: Int, // значение актуально только при status == CLOSED
    @ColumnInfo("totalPrice") val totalPrice: Double, // значение актуально только при status == CLOSED
    @ColumnInfo("createdAtEpochMs") val createdAtEpochMs: Long,
    @ColumnInfo("closedAtEpochMs") val closedAtEpochMs: Long? = null
)
