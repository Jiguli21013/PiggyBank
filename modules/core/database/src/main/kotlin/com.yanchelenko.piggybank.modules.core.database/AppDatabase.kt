package com.yanchelenko.piggybank.modules.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yanchelenko.piggybank.modules.core.database.dao.ScannedProductDao
import com.yanchelenko.piggybank.modules.core.database.models.ScannedProductDBO
import com.yanchelenko.piggybank.modules.core.database.dao.CartDao
import com.yanchelenko.piggybank.modules.core.database.dao.ProductDao
import com.yanchelenko.piggybank.modules.core.database.dao.ProductOfCartDao
import com.yanchelenko.piggybank.modules.core.database.dao.ProductVersionDao
import com.yanchelenko.piggybank.modules.core.database.models.CartDBO
import com.yanchelenko.piggybank.modules.core.database.models.CartItemDBO
import com.yanchelenko.piggybank.modules.core.database.models.ProductDBO
import com.yanchelenko.piggybank.modules.core.database.models.ProductVersionDBO

//todo database — это infrastructure layer, - переместить в infrastracture module ?
@TypeConverters(BigDecimalConverter::class)
@Database(
    entities = [
        ScannedProductDBO::class,
        ProductDBO::class,
        ProductVersionDBO::class,
        CartDBO::class,
        CartItemDBO::class,
    ],
    version = 5,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun productsDao(): ScannedProductDao
    abstract fun cartDao(): CartDao
    abstract fun cartItemDao(): ProductOfCartDao

    abstract fun productDao(): ProductDao
    abstract fun productVersionDao(): ProductVersionDao
}
