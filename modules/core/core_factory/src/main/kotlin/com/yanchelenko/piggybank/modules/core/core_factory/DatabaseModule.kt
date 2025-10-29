package com.yanchelenko.piggybank.modules.core.core_factory

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yanchelenko.piggybank.modules.core.database.AppDatabase
import com.yanchelenko.piggybank.modules.core.database.dao.CartDao
import com.yanchelenko.piggybank.modules.core.database.dao.ProductOfCartDao
import com.yanchelenko.piggybank.modules.core.database.dao.ScannedProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Единая база данных приложения: хранит и продукты, и корзину.
     * Добавляем onCreate callback — создаёт индексы при первом создании БД.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "piggybank.db"
        )
            // Раз проекта ещё нет в релизе — просто пересоздаём схему при изменениях
            .fallbackToDestructiveMigration()

            // Добавляем частичный индекс для одной активной корзины
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // 1️⃣ Индекс для ускоренного поиска по status
                    db.execSQL(sql = "CREATE INDEX IF NOT EXISTS idx_carts_status ON carts(status)")

                    // 2️⃣ Частичный UNIQUE индекс: максимум одна ACTIVE корзина
                    db.execSQL(
                        """
                        CREATE UNIQUE INDEX IF NOT EXISTS idx_one_active_cart
                        ON carts(status)
                        WHERE status = 'ACTIVE'
                        """.trimIndent()
                    )
                }
            })
            .build()
    }

    /** DAO для таблицы продуктов */
    @Provides
    fun provideProductDao(db: AppDatabase): ScannedProductDao = db.productsDao()

    /** DAO для корзины */
    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

    /** DAO для элементов корзины */
    @Provides
    fun provideCartItemDao(db: AppDatabase): ProductOfCartDao = db.cartItemDao()

}
