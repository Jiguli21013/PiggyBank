package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.UpdateProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.core.core_api.repository.ScannedProductsRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class UpdateProductUseCaseImpl @Inject constructor(
    private val repository: ScannedProductsRepository,
    private val logger: Logger
) : UpdateProductUseCase {

    override suspend operator fun invoke(scannedProduct: ScannedProduct): RequestResult<Unit> {
        val now: Instant = Clock.System.now()
        val productWithTimestamp = scannedProduct.copy(addedAt = now) //todo поменять addedAt на updatedAt

        logger.d(LOG_TAG, "Updating scannedProduct: $productWithTimestamp")

        return repository.updateProductDatabase(scannedProduct = productWithTimestamp)
            .mapCatching { updated ->
                if (updated) {
                    logger.d(LOG_TAG, "ScannedProduct updated successfully")
                } else {
                    logger.e(LOG_TAG, "DB update returned false — no rows affected")
                    error("DB update failed") //todo exception
                }
            }
            .toRequestResult()
    }

    private companion object {
        const val LOG_TAG = "UpdateProductUseCaseImpl"
    }
}
