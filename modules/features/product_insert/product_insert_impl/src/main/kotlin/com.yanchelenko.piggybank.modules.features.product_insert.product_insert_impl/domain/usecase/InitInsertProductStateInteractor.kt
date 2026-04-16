package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.combineResults
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductWithCurrentVersionByBarcodeUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.GetProductWithCurrentVersionByBarcodeResult
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductInCart
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
    private val getProductWithCurrentVersionByBarcodeUseCase: GetProductWithCurrentVersionByBarcodeUseCase,
    private val getProductInActiveCartUseCase: GetProductInActiveCartUseCaseImpl,
    private val logger: Logger,
) {

    suspend operator fun invoke(barcode: String): RequestResult<ProductInitResultDomain> = coroutineScope {
        val productDeferred = async {
            getProductWithCurrentVersionByBarcodeUseCase(barcode = barcode)
        }
        val quantityDeferred = async {
            getProductInActiveCartUseCase(barcode = barcode)
        }

        val productResult = productDeferred.await()
        val inCartResult = quantityDeferred.await()

        logger.d(LOG_TAG, "productResult=$productResult")
        logger.d(LOG_TAG, "inCartResult=$inCartResult")

        combineResults(
            first = productResult,
            second = inCartResult,
        ) { combinedProductResult: GetProductWithCurrentVersionByBarcodeResult?, inCart: ProductInCart? ->
            logger.d(
                LOG_TAG,
                "Combining -> productResult=$combinedProductResult, inCartQty=${inCart?.quantity}, inCartId=${inCart?.itemId}"
            )
            val resolvedProductResult = combinedProductResult
                ?: GetProductWithCurrentVersionByBarcodeResult.NotFound
            ProductInitResultDomain(
                barcode = barcode,
                productResult = resolvedProductResult,
                quantityInCart = inCart?.quantity ?: 0,
                cartItemId = inCart?.itemId,
                isInCart = (inCart?.quantity ?: 0) > 0,
            )
        }
    }

    private companion object {
        const val LOG_TAG = "InitInsertProductStateInteractor"
    }
}

/**
 * Pure domain DTO for initializing the InsertProduct flow.
 * - [productResult] explicitly describes whether the product was found,
 *   not found, or found without a current version.
 * - [quantityInCart] is 0 when the product is not present in the active cart.
 * - [cartItemId] id of the product if it is present in the active cart.
 * - [isInCart] indicates whether the product is present in the active cart.
 */
data class ProductInitResultDomain(
    val barcode: String,
    val productResult: GetProductWithCurrentVersionByBarcodeResult,
    val quantityInCart: Int,
    val cartItemId: Long?,
    val isInCart: Boolean,
)
