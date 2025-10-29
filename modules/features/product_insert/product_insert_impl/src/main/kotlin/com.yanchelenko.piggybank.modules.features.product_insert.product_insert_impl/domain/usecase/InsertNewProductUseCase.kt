package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.core.core_api.repository.ScannedProductsRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class InsertNewProductUseCase @Inject constructor(
    private val repository: ScannedProductsRepository,
    private val logger: Logger
) {
    suspend operator fun invoke(scannedProduct: ScannedProduct): RequestResult<Unit> {
        val now: Instant = Clock.System.now()
        val productWithTimestamp = scannedProduct.copy(addedAt = now)
        logger.d(LOG_TAG, "Inserting scannedProduct: $productWithTimestamp")
        return repository.saveScannedProductToDatabase(scannedProduct = productWithTimestamp)
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
