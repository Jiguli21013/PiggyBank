package com.yanchelenko.piggybank.modules.core.core_api.repository

import androidx.paging.PagingData
import com.yanchelenko.piggybank.modules.core.core_api.models.Cart
import com.yanchelenko.piggybank.modules.core.core_api.models.CartTotals
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductInCart
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getPagedCarts(): Flow<PagingData<Cart>>

    /**
     * Получение продуктов с использованием пагинации.
     */
    fun getPagedCartProducts(): Flow<PagingData<ProductOfCart>>

    /** Live totals of the active cart. */
    fun observeActiveCartTotals(): Flow<CartTotals>

    /**
     * Получение продукта из корзины по ID.
     */
    suspend fun getCartProductById(cartProductId: Long): Result<ProductOfCart>

    /**
     * Добавить товар в текущую (или новую) активную корзину.
     *
     * Если активной корзины нет, реализация должна создать её (по cartId) и затем добавить позицию.
     * Возвращает Result с идентификатором созданной позиции корзины (id строки в cart_items).
     */
    suspend fun addProductToCart(productOfCart: ProductOfCart): Result<Long>

    suspend fun removeProductFromCart(productOfCartId: Long): Result<Long>

    /**
     * Returns the quantity of the product with the given barcode in the active cart.
     * Return cartItemId of the product in cart.
     */
    suspend fun getProductInActiveCartByBarcode(barcode: String): Result<ProductInCart>

    /**
     * Closes the active cart if present.
     * @return Result<Boolean> — true if a cart was closed, false if there was no active cart.
     */
    suspend fun closeActiveCart(totalItems: Int, totalPrice: Double): Result<Boolean>
}
