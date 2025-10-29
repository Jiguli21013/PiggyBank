package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.flatMap
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.ProductNotFoundException
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.core.core_api.repository.ScannedProductsRepository
import javax.inject.Inject

class GetProductByBarcodeUseCase @Inject constructor(
    private val repository: ScannedProductsRepository,
    private val logger: Logger
) {
    suspend operator fun invoke(barcode: String): RequestResult<ScannedProduct> {
        return repository
            .getScannedProductByBarcode(barcode = barcode)
            .flatMap { product ->
                if (product != null) {
                    logger.d(LOG_TAG, "ScannedProduct found: $product")
                    Result.success(value = product)
                } else {
                    logger.d(LOG_TAG, "ScannedProduct not found for barcode: $barcode")
                    Result.failure(exception = ProductNotFoundException(barcode))
                }
            }
            .toRequestResult()
    }

    private companion object {
        const val LOG_TAG = "GetProductByBarcodeUseCase"
    }
}
