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
     * Unified application database: stores both products and cart.
     * Adds onCreate callback — creates indexes when the DB is first created.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "piggybank.db"
        )
            // the project is not in release yet - recreate the schema on changes
            .fallbackToDestructiveMigration()

            // Add a partial index for one active cart
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Index for faster search by status
                    db.execSQL(sql = "CREATE INDEX IF NOT EXISTS idx_carts_status ON carts(status)")

                    // Partial UNIQUE index: at most one ACTIVE cart
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

    /** DAO for the products table */
    @Provides
    fun provideProductDao(db: AppDatabase): ScannedProductDao = db.productsDao()

    /** DAO for the cart table */
    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

    /** DAO for the cart items table */
    @Provides
    fun provideCartItemDao(db: AppDatabase): ProductOfCartDao = db.cartItemDao()
}
