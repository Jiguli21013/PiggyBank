package com.yanchelenko.piggybank.domain

import com.yanchelenko.piggybank.logger.Logger
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.repository.ProductsRepository
import com.yanchelenko.piggybank.result.RequestResult
import com.yanchelenko.piggybank.result.flatMap
import com.yanchelenko.piggybank.result.toRequestResult
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
                run {
                    logger.d(LOG_TAG, "Product found: $product")
                    Result.success(value = product)
                }
            }
            .toRequestResult()
    }

    companion object {
        private const val LOG_TAG = "GetProductByIdUseCaseImpl"
    }
}
