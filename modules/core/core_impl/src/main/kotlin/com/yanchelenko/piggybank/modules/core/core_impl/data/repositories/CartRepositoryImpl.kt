package com.yanchelenko.piggybank.modules.core.core_impl.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Cart
import com.yanchelenko.piggybank.modules.core.core_api.models.CartTotals
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductInCart
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toDomain
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toProductOfCart
import com.yanchelenko.piggybank.modules.core.database.dao.CartDao
import com.yanchelenko.piggybank.modules.core.database.dao.ProductOfCartDao
import com.yanchelenko.piggybank.modules.core.database.models.CartDBO
import com.yanchelenko.piggybank.modules.core.database.models.CartItemDBO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val productOfCartDao: ProductOfCartDao,
    private val logger: Logger
) : CartRepository {

    /**
     * Returns paged list of products from the current active cart.
     */
    override fun getPagedCartProducts(): Flow<PagingData<ProductOfCart>> = flow {
        // Retrieve active cart ID safely on IO dispatcher
        val activeCartId = withContext(Dispatchers.IO) {
            cartDao.getActiveCartId()
        }

        if (activeCartId == null) {
            logger.d(LOG_TAG, "No active cart found → returning empty list")
            emit(PagingData.empty())
            return@flow
        }

        val pagingFlow = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                productOfCartDao.getPagedCarts(cartId = activeCartId)
            }
        ).flow.map { data -> data.map { it.toProductOfCart() } }

        emitAll(pagingFlow)
    }

    override fun observeActiveCartTotals(): Flow<CartTotals> =
        productOfCartDao.observeTotalsForActiveCart()
            .map { it.toDomain() }

    override fun getPagedCarts(): Flow<PagingData<Cart>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false)
        ) {
            cartDao.pagingSourceClosedCarts()
        }.flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }

    /**
     * Returns a single product from the cart by its ID.
     */
    override suspend fun getCartProductById(cartProductId: Long): Result<ProductOfCart> = runCatching {
        withContext(Dispatchers.IO) {
            val dbo = productOfCartDao.getById(id = cartProductId)
                ?: throw NoSuchElementException("Cart item not found: $cartProductId")
            dbo.toProductOfCart()
        }
    }

    /**
     * Adds a product to the active cart or creates a new one if none exists.
     */
    override suspend fun addProductToCart(productOfCart: ProductOfCart): Result<Long> = runCatching {
        withContext(Dispatchers.IO) {
            val activeCartId = cartDao.getActiveCartId() ?: createNewActiveCart()

            val dbo = CartItemDBO(
                cartId = activeCartId,
                productId = productOfCart.productId?.takeIf { it > 0L }, // todo подумать над красотой
                barcode = productOfCart.barcode,
                name = productOfCart.name,
                unitPrice = productOfCart.unitPrice,
                isWeightBased = productOfCart.isWeightBased,
                weightGrams = productOfCart.weightGrams,
                quantity = productOfCart.quantity,
                createdAtEpochMs = System.currentTimeMillis()
            )

            val insertedId = productOfCartDao.insert(dbo)
            logger.d(LOG_TAG, "Product '${productOfCart.name}' added to cart $activeCartId (id=$insertedId)")
            insertedId
        }
    }

    /**
     * Removes a product strictly from the active cart.
     */
    override suspend fun removeProductFromCart(productOfCartId: Long): Result<Long> = runCatching {
        withContext(Dispatchers.IO) {
            val rows = productOfCartDao.deleteProductOfCartFromActiveCart(productOfCartId = productOfCartId)
            if (rows == 0) {
                throw NoSuchElementException("Cart item not found in active cart: $productOfCartId")
            }
            productOfCartId
        }
    }

    override suspend fun getProductInActiveCartByBarcode(barcode: String): Result<ProductInCart> = runCatching {
        withContext(Dispatchers.IO) {
            val activeCartId = cartDao.getActiveCartId() ?: createNewActiveCart()
            val productInCartDbo = productOfCartDao.getProductInActiveCartByBarcode(
                cartId = activeCartId,
                barcode = barcode
            )
            logger.d(
                LOG_TAG,
                "Active cart $activeCartId, barcode=$barcode -> quantity=${productInCartDbo?.quantity} and item in cart id = ${productInCartDbo?.itemId}"
            )
            productInCartDbo.toDomain()
        }
    }

    /**
     * Creates a new active cart if none exists.
     */
    private suspend fun createNewActiveCart(): Long {
        val newCart = CartDBO(
            id = 0, // auto-generate ID
            storeId = null, //todo implement the choice of the shop
            status = "ACTIVE",
            totalItems = 0, // it can be set to 0 because when closing the cart we update the value to the actual one
            totalPrice = 0.0, // it can be set to 0 because when closing the cart we update the value to the actual one
            createdAtEpochMs = System.currentTimeMillis()
        )
        val newId = cartDao.insert(cart = newCart)
        logger.d(LOG_TAG, "New cart created with id=$newId")
        return newId
    }

    override suspend fun closeActiveCart(
        totalItems: Int,
        totalPrice: Double
    ): Result<Boolean> = runCatching {
        withContext(Dispatchers.IO) {
            val affected = cartDao.closeActiveCart(
                totalItems = totalItems,
                totalPrice = totalPrice,
                timestamp = System.currentTimeMillis()
            )
            logger.d(LOG_TAG, "closeActiveCart affected=$affected")
            affected > 0
        }
    }

    private companion object {
        const val LOG_TAG = "CartRepository"
    }

}
