package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import kotlinx.datetime.Instant

interface SaveProductVersionIfChangedUseCase {
    suspend operator fun invoke(
        params: Params,
    ): RequestResult<SaveProductVersionIfChangedResult>

    data class Params(
        val barcode: String,
        val productName: String,
        val weightGrams: Int,
        val price: Double,
        val pricePerKg: Double,
        val changedAt: Instant,
    )
}

sealed interface SaveProductVersionIfChangedResult {

    data class NewProductCreated(
        val productId: Long,
        val versionId: Long,
    ) : SaveProductVersionIfChangedResult

    data class NewVersionCreated(
        val productId: Long,
        val previousVersionId: Long,
        val newVersionId: Long,
    ) : SaveProductVersionIfChangedResult

    data class NoChanges(
        val productId: Long,
        val currentVersionId: Long,
    ) : SaveProductVersionIfChangedResult

}