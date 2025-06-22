package com.yanchelenko.piggybank.domain.usecases

import com.yanchelenko.piggybank.domain.exceptions.ProductNotFoundException
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.Logger
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import com.yanchelneko.piggybank.common.core_utils.flatMap
import com.yanchelneko.piggybank.common.core_utils.toRequestResult
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) {
    suspend operator fun invoke(productId: Long): RequestResult<Product> {
        logger.d(LOG_TAG, "Fetching product by ID: $productId")

        return repository
            .getProductById(productId = productId)
            .flatMap { product ->
                if (product != null) {
                    logger.d(LOG_TAG, "Product found: $product")
                    Result.success(value = product)
                } else {
                    logger.e(LOG_TAG, "Product with ID $productId not found")
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
        private const val LOG_TAG = "GetProductByIdUseCase"
    }
}
