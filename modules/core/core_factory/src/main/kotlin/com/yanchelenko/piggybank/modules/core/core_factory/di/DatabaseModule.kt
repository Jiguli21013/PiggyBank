package com.yanchelenko.piggybank.modules.core.core_factory.di

import android.content.Context
import androidx.room.Room
import com.yanchelenko.piggybank.modules.core.database.ProductsRoomDatabase
import com.yanchelenko.piggybank.modules.core.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideProductsDatabase(
        @ApplicationContext context: Context
    ): ProductsRoomDatabase {
        return Room.databaseBuilder(
            context,
            ProductsRoomDatabase::class.java,
            "products.db"
        ).build()
    }

    @Provides
    fun provideProductDao(
        database: ProductsRoomDatabase
    ): ProductDao = database.productsDao()
}
