package com.yanchelenko.piggybank.features.product_insert.domain.usecases

import com.yanchelenko.piggybank.domain.exceptions.ProductNotFoundException
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import com.yanchelneko.piggybank.common.core_utils.flatMap
import com.yanchelneko.piggybank.common.core_utils.toRequestResult
import javax.inject.Inject

class GetProductByBarcodeUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(barcode: String): RequestResult<Product> {
        return repository
            .getProductByBarcode(barcode = barcode)
            .flatMap { product ->
                if (product != null) {
                    Result.success(value = product)
                } else {
                    Result.failure(exception = ProductNotFoundException(barcode))
                }
            }
            .toRequestResult()
    }
}
