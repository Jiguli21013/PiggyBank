package com.yanchelenko.piggybank.modules.core.core_api.models

sealed interface GetProductWithCurrentVersionByBarcodeResult {
    data class Found(
        val product: ProductWithCurrentVersion
    ) : GetProductWithCurrentVersionByBarcodeResult

    data object NotFound : GetProductWithCurrentVersionByBarcodeResult

    data class ProductWithoutCurrentVersion(
        val product: Product
    ) : GetProductWithCurrentVersionByBarcodeResult
}
