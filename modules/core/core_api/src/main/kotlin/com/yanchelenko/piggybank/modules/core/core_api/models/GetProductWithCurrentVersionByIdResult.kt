package com.yanchelenko.piggybank.modules.core.core_api.models

sealed interface GetProductWithCurrentVersionByIdResult {

    data class Found(
        val product: ProductWithCurrentVersion,
    ) : GetProductWithCurrentVersionByIdResult

    data object NotFound : GetProductWithCurrentVersionByIdResult

    data class ProductWithoutCurrentVersion(
        val product: Product,
    ) : GetProductWithCurrentVersionByIdResult
}
