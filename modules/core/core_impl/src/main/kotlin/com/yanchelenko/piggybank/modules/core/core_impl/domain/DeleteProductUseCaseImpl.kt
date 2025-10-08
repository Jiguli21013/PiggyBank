package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductsRepository
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
            .onFailure { throwable -> logger.e(LOG_TAG, "Failed to delete product: ${throwable.message}") }
            .toRequestResult()
    }

    private companion object {
        const val LOG_TAG = "DeleteProductUseCaseImpl"
    }
}

