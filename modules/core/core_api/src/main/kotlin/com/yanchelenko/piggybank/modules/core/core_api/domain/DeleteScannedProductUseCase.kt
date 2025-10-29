package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct

interface DeleteScannedProductUseCase {
    suspend operator fun invoke(scannedProduct: ScannedProduct): RequestResult<Unit>
}
