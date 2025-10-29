package com.yanchelenko.piggybank.modules.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yanchelenko.piggybank.modules.core.database.models.CartItemDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductOfCartDao {
    @Insert
    suspend fun insert(item: CartItemDBO): Long

    @Query("SELECT * FROM cart_items WHERE cartId=:cartId ORDER BY createdAtEpochMs DESC")
    fun getPagedCarts(cartId: Long): PagingSource<Int, CartItemDBO>

    /** Получить одну позицию корзины по её ID */
    @Query("SELECT * FROM cart_items WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): CartItemDBO?

    @Query("UPDATE cart_items SET quantity=:quantity WHERE id=:id")
    suspend fun updateQuantity(id: Long, quantity: Int)

    @Query(
        """
        DELETE FROM cart_items
        WHERE cartId = (SELECT id FROM carts WHERE status='ACTIVE' LIMIT 1)
          AND id = :productOfCartId
    """
    )
    suspend fun deleteProductOfCartFromActiveCart(productOfCartId: Long): Int

    @Query("DELETE FROM cart_items WHERE cartId=:cartId")
    suspend fun clearCart(cartId: Long)

    /** Returns both quantity and itemId for a product in the given cart by barcode. */
    @Query(
        """
    SELECT 
        quantity AS quantity,
        id AS itemId
    FROM cart_items 
    WHERE cartId = :cartId 
      AND barcode = :barcode 
    LIMIT 1
    """
    )
    suspend fun getProductInActiveCartByBarcode(
        cartId: Long,
        barcode: String
    ): ProductInCartDBO?

    @Query("""
    SELECT
        COALESCE(SUM(quantity), 0) AS itemsCount,
        COALESCE(SUM(CASE WHEN isWeightBased THEN (COALESCE(weightGrams, 0) * quantity) ELSE 0 END), 0) AS totalWeightGrams,
        COALESCE(SUM(
            CASE
                WHEN isWeightBased THEN (unitPrice * (COALESCE(weightGrams, 0) / 1000.0) * quantity)
                ELSE (unitPrice * quantity)
            END
        ), 0.0) AS totalPrice
    FROM cart_items
    WHERE cartId = (SELECT id FROM carts WHERE status = 'ACTIVE' LIMIT 1)
""")
    fun observeTotalsForActiveCart(): Flow<CartTotalsRawDBO>
}

data class CartTotalsRawDBO(
    val itemsCount: Int,
    val totalWeightGrams: Int,
    val totalPrice: Double
)

data class ProductInCartDBO(
    val quantity: Int,
    val itemId: Long
)