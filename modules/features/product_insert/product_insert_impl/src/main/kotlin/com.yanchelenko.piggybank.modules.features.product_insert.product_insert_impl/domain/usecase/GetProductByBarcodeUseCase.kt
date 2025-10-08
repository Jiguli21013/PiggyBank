package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.flatMap
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.ProductNotFoundException
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductsRepository
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
