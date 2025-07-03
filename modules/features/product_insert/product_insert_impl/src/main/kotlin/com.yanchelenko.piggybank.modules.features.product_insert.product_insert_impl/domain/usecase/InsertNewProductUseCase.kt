package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductsRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class InsertNewProductUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) {
    suspend operator fun invoke(product: Product): RequestResult<Unit> {
        val now: Instant = Clock.System.now()
        val productWithTimestamp = product.copy(addedAt = now)
        logger.d(LOG_TAG, "Inserting product: $productWithTimestamp")
        return repository.saveProductToDatabase(product = productWithTimestamp)
            .toRequestResult()
            .also {
                if (it is RequestResult.Error) {
                    logger.e(LOG_TAG, "Insert result error: ${it.error?.message}")
                } else {
                    logger.d(LOG_TAG, "Insert result: $it")
                }
            }
    }

    private companion object {
        const val LOG_TAG = "InsertNewProductUseCase"
    }
}
