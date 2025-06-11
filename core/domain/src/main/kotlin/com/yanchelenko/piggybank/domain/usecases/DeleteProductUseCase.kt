package com.yanchelenko.piggybank.domain.usecases

import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import com.yanchelneko.piggybank.common.core_utils.toRequestResult
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(product: Product): RequestResult<Unit> {
        return repository
            .deleteProductFromDatabase(product = product)
            .toRequestResult()
    }
}

