package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.core.core_api.models.GetProductWithCurrentVersionByBarcodeResult

interface GetProductWithCurrentVersionByBarcodeUseCase {
    suspend operator fun invoke(barcode: String): RequestResult<GetProductWithCurrentVersionByBarcodeResult>
}
