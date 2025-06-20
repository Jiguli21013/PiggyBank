package com.yanchelenko.piggybank.di

import android.content.Context
import androidx.room.Room
import com.yanchelenko.piggybank.core.database.ProductsRoomDatabase
import com.yanchelenko.piggybank.core.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsDatabase(
        @ApplicationContext context: Context
    ): ProductsRoomDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = ProductsRoomDatabase::class.java,
            name = "products.db"
        ).build()
    }

    @Provides
    fun provideProductDao(
        database: ProductsRoomDatabase
    ): ProductDao {
        return database.productsDao()
    }

    //@Provides
    //@Singleton
    //fun provideAppCoroutineDispatchers(): AppDispatchers = AppDispatchers()

    //@Provides
    //fun provideLogger(): Logger = AndroidLogcatLogger()
}
