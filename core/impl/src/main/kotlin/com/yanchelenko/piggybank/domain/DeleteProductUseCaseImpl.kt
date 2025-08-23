package com.yanchelenko.piggybank.domain

import com.yanchelenko.piggybank.logger.Logger
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.repository.ProductsRepository
import com.yanchelenko.piggybank.result.RequestResult
import com.yanchelenko.piggybank.result.toRequestResult
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) : DeleteProductUseCase {
    override suspend operator fun invoke(product: Product): RequestResult<Unit> {
        logger.d(LOG_TAG, "Attempting to delete product: $product")

        return repository
            .deleteProductFromDatabase(product = product)
            .onSuccess { logger.d(LOG_TAG, "Product deleted successfully: id=${product.id}") }
            .onFailure { throwable ->
                logger.e(
                    LOG_TAG,
                    "Failed to delete product: ${throwable.message}"
                )
            }
            .toRequestResult()
    }

    private companion object {
        const val LOG_TAG = "DeleteProductUseCaseImpl"
    }
}

