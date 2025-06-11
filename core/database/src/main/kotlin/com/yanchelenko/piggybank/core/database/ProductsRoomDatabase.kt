package com.yanchelenko.piggybank.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yanchelenko.piggybank.core.database.dao.ProductDao
import com.yanchelenko.piggybank.core.database.models.ProductDBO

@Database(
    entities = [ProductDBO::class],
    version = 1,
    exportSchema = false
)

abstract class ProductsRoomDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductDao
}
