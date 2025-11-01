package com.yanchelenko.piggybank.modules.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yanchelenko.piggybank.modules.core.database.dao.ScannedProductDao
import com.yanchelenko.piggybank.modules.core.database.models.ScannedProductDBO
import com.yanchelenko.piggybank.modules.core.database.dao.CartDao
import com.yanchelenko.piggybank.modules.core.database.dao.ProductOfCartDao
import com.yanchelenko.piggybank.modules.core.database.models.CartDBO
import com.yanchelenko.piggybank.modules.core.database.models.CartItemDBO

//todo database — это infrastructure layer, - переместить в infrastracture module ?
@TypeConverters(BigDecimalConverter::class)
@Database(
    entities = [
        ScannedProductDBO::class,
        CartDBO::class,
        CartItemDBO::class,
    ],
    version = 3,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun productsDao(): ScannedProductDao
    abstract fun cartDao(): CartDao
    abstract fun cartItemDao(): ProductOfCartDao
}
