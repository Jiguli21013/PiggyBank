package com.yanchelenko.piggybank.features.product_insert.domain.usecases

import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import com.yanchelneko.piggybank.common.core_utils.toRequestResult
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class InsertNewProductUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(product: Product): RequestResult<Unit> {
        val now: Instant = Clock.System.now()
        val productWithTimestamp = product.copy(addedAt = now)
        return repository.saveProductToDatabase(product = productWithTimestamp).toRequestResult()
    }
}
