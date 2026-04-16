package com.yanchelenko.piggybank.modules.core.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    indices = [
        Index(value = ["barcode"], unique = true)
    ]
)
data class ProductDBO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "barcode")
    val barcode: String,

    @ColumnInfo(name = "productName")
    val productName: String,

    @ColumnInfo(name = "createdAtEpochMs")
    val createdAtEpochMs: Long,
)
