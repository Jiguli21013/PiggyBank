package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductWithCurrentVersionByBarcodeUseCase
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductRepository
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.core.core_api.models.GetProductWithCurrentVersionByBarcodeResult
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductWithCurrentVersion
import javax.inject.Inject

class GetProductWithCurrentVersionByBarcodeUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
) : GetProductWithCurrentVersionByBarcodeUseCase {

    override suspend fun invoke(barcode: String): RequestResult<GetProductWithCurrentVersionByBarcodeResult> {
        return runCatching {
            val product = productRepository
                .getProductByBarcode(barcode = barcode)
                .getOrThrow()
                ?: return@runCatching GetProductWithCurrentVersionByBarcodeResult.NotFound

            val currentVersion = productRepository
                .getCurrentVersion(productId = product.id)
                .getOrThrow()
                ?: return@runCatching GetProductWithCurrentVersionByBarcodeResult.ProductWithoutCurrentVersion(
                    product = product,
                )

            GetProductWithCurrentVersionByBarcodeResult.Found(
                product = ProductWithCurrentVersion(
                    product = product,
                    currentVersion = currentVersion,
                )
            )
        }.toRequestResult()
    }
}
