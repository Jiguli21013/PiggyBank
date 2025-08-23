package com.yanchelenko.piggybank.usecase

import com.yanchelenko.piggybank.exceptions.ProductNotFoundException
import com.yanchelenko.piggybank.logger.Logger
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.repository.ProductsRepository
import com.yanchelenko.piggybank.result.RequestResult
import com.yanchelenko.piggybank.result.flatMap
import com.yanchelenko.piggybank.result.toRequestResult
import javax.inject.Inject

class GetProductByBarcodeUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) {
    suspend operator fun invoke(barcode: String): RequestResult<Product> {
        return repository
            .getProductByBarcode(barcode = barcode)
            .flatMap { product ->
                if (product != null) {
                    logger.d(LOG_TAG, "Product found: $product")
                    Result.success(value = product)
                } else {
                    logger.d(LOG_TAG, "Product not found for barcode: $barcode")
                    Result.failure(exception = ProductNotFoundException(barcode))
                }
            }
            .toRequestResult()
    }

    private companion object {
        const val LOG_TAG = "GetProductByBarcodeUseCase"
    }
}
