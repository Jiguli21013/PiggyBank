package com.yanchelenko.piggybank.domain.usecases

import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.Logger
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import com.yanchelneko.piggybank.common.core_utils.toRequestResult
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) {
    suspend operator fun invoke(product: Product): RequestResult<Unit> {
        logger.d(LOG_TAG, "Attempting to delete product: $product")

        return repository
            .deleteProductFromDatabase(product = product)
            .onSuccess { logger.d(LOG_TAG, "Product deleted successfully: id=${product.id}") }
            .onFailure { throwable -> logger.e(LOG_TAG, "Failed to delete product: ${throwable.message}") }
            .toRequestResult()
    }

    private companion object {
        const val LOG_TAG = "DeleteProductUseCase"
    }
}

