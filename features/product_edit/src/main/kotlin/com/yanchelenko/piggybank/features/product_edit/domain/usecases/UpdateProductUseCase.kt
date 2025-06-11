package com.yanchelenko.piggybank.features.product_edit.domain.usecases

import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import com.yanchelneko.piggybank.common.core_utils.toRequestResult
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(product: Product): RequestResult<Unit> {
        val now: Instant = Clock.System.now()
        val productWithTimestamp = product.copy(addedAt = now) //todo поменять addedAt на updatedAt

        return repository.updateProductDatabase(product = productWithTimestamp)
            .mapCatching { updated ->
                if (updated) {
                    Unit
                } else {
                    error("DB update failed") //todo
                }
            }
            .toRequestResult()
    }
}
