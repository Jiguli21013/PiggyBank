package com.yanchelenko.piggybank.modules.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yanchelenko.piggybank.modules.core.database.models.CartDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert
    suspend fun insert(cart: CartDBO): Long

    /** Полная запись активной корзины */
    @Query("SELECT * FROM carts WHERE status='ACTIVE' LIMIT 1")
    suspend fun getActiveCart(): CartDBO?

    @Query("SELECT * FROM carts WHERE status = 'CLOSED' ORDER BY closedAtEpochMs DESC")
    fun pagingSourceClosedCarts(): PagingSource<Int, CartDBO>

    /** Наблюдение за активной корзиной */
    @Query("SELECT * FROM carts WHERE status='ACTIVE' LIMIT 1")
    fun observeActiveCart(): Flow<CartDBO?>

    /** Идентификатор активной корзины (suspend) */
    @Query("SELECT id FROM carts WHERE status='ACTIVE' LIMIT 1")
    suspend fun getActiveCartId(): Long?

    /**
     * Идентификатор активной корзины (blocking).
     * Нужен, например, для PagingSource фабрики.
     */
    @Query("SELECT id FROM carts WHERE status='ACTIVE' LIMIT 1")
    fun getActiveCartIdBlocking(): Long?

    /** Закрыть корзину по id и обновить totalItems и totalPrice */
    @Query("""
    UPDATE carts
    SET 
        status = 'CLOSED',
        closedAtEpochMs = :timestamp,
        totalItems = :totalItems,
        totalPrice = :totalPrice
    WHERE status = 'ACTIVE'
""")
    suspend fun closeActiveCart(
        timestamp: Long,
        totalItems: Int,
        totalPrice: Double
    ): Int
}
