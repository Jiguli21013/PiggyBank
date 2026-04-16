package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteScannedProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductRepository
import javax.inject.Inject
import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository


class DeleteScannedProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val logger: Logger
) : DeleteScannedProductUseCase {

    override suspend operator fun invoke(scannedProduct: ScannedProduct): RequestResult<Unit> {
        logger.d(LOG_TAG, "Attempting to delete scannedProduct: $scannedProduct")

        if (cartRepository.isProductInActiveCart(productId = scannedProduct.id)) {
            val exception = IllegalStateException("Scanned product is in active cart")
            logger.e(
                LOG_TAG,
                "Failed to delete scannedProduct: product is in active cart, id=${scannedProduct.id}"
            )
            return Result.failure<Unit>(exception).toRequestResult()
        }

        return productRepository
            .deleteProduct(productId = scannedProduct.id)
            .map { Unit }
            .onSuccess {
                logger.d(LOG_TAG, "ScannedProduct deleted successfully: id=${scannedProduct.id}")
            }
            .onFailure { throwable ->
                logger.e(LOG_TAG, "Failed to delete scannedProduct: ${throwable.message}")
            }
            .toRequestResult()
    }

    private companion object {
        const val LOG_TAG = "DeleteScannedProductUseCaseImpl"
    }
}

