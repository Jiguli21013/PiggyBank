package com.yanchelenko.piggybank

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yanchelenko.piggybank.dao.ProductDao
import com.yanchelenko.piggybank.models.ProductDBO

@Database(
    entities = [ProductDBO::class],
    version = 1,
    exportSchema = false
)

abstract class ProductsRoomDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductDao
}
