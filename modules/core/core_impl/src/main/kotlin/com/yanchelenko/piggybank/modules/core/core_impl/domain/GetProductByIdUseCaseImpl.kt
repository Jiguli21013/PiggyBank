package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.flatMap
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.ProductNotFoundException
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductsRepository
import javax.inject.Inject

class GetProductByIdUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) : GetProductByIdUseCase {

    override suspend operator fun invoke(productId: Long): RequestResult<Product> {
        logger.d(LOG_TAG, "Fetching product by ID: $productId")

        return repository
            .getProductById(productId = productId)
            .flatMap { product ->
                if (product != null) {
                    logger.d(LOG_TAG, "Product found: $product")
                    Result.success(value = product)
                } else {
                    logger.e(LOG_TAG, "Product with ID: $productId not found")
                    Result.failure(
                        exception = ProductNotFoundException(
                            barcode = productId.toString()
                        )
                    )
                }
            }
            .toRequestResult()
    }

    companion object {
        private const val LOG_TAG = "GetProductByIdUseCaseImpl"
    }
}
