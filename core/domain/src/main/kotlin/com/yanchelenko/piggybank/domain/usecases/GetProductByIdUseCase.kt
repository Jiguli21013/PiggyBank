package com.yanchelenko.piggybank.domain.usecases

import com.yanchelenko.piggybank.domain.exceptions.ProductNotFoundException
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import com.yanchelneko.piggybank.common.core_utils.flatMap
import com.yanchelneko.piggybank.common.core_utils.toRequestResult
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(productId: Long): RequestResult<Product> {
        return repository
            .getProductById(productId = productId)
            .flatMap { product ->
                if (product != null) {
                    Result.success(value = product)
                } else {
                    Result.failure(
                        exception = ProductNotFoundException(
                            barcode = productId.toString()
                        )
                    )
                }
            }
            .toRequestResult()
    }
}
