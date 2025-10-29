package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.combineResults
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductInCart
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

/**
 * Domain-level orchestrator that initializes data needed for InsertProduct flow.
 * Runs two operations in parallel:
 *  - loads product by barcode (domain entity)
 *  - gets product in the active cart
 *
 * Returns a single consolidated domain DTO [ProductInitResultDomain].
 */
class InitInsertProductStateInteractor @Inject constructor(
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getProductInActiveCartUseCase: GetProductInActiveCartUseCaseImpl,
    private val logger: Logger,
) {

    suspend operator fun invoke(barcode: String): RequestResult<ProductInitResultDomain> = coroutineScope {
        val productDeferred = async { getProductByBarcodeUseCase(barcode = barcode) } // RequestResult<ScannedProduct>
        val quantityDeferred = async { getProductInActiveCartUseCase(barcode = barcode) } // RequestResult<ProductInCart>

        val productResult = productDeferred.await()
        val inCartResult = quantityDeferred.await()

        logger.d(LOG_TAG, "productResult=$productResult")
        logger.d(LOG_TAG, "inCartResult=$inCartResult")

        combineResults(
            first = productResult,
            second = inCartResult
        ) { product: ScannedProduct?, inCart: ProductInCart? ->
            logger.d(LOG_TAG, "Combining -> product=${product?.productName}, price=${product?.price}, inCartQty=${inCart?.quantity}, inCartId=${inCart?.itemId}")
            ProductInitResultDomain(
                barcode = barcode,
                product = product,
                quantityInCart = inCart?.quantity ?: 0,
                cartItemId = inCart?.itemId,
                isInCart = (inCart?.quantity ?: 0) > 0
            )
        }
    }

    private companion object {
        const val LOG_TAG = "InitInsertProductStateInteractor"
    }
}

/**
 * Pure domain DTO for initializing the InsertProduct flow.
 * - [product] may be null if it wasn't found; the ViewModel maps this to a UI placeholder.
 * - [quantityInCart] is 0 when the product is not present in the active cart.
 * - [cartItemId] id of the product is present in the active cart.
 * - [isInCart] indicates whether the product is present in the active cart.
 */
data class ProductInitResultDomain(
    val barcode: String,
    val product: ScannedProduct?,
    val quantityInCart: Int,
    val cartItemId: Long?,
    val isInCart: Boolean
)
