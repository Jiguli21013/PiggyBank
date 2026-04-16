package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductWithCurrentVersionByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.GetProductWithCurrentVersionByIdResult
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductWithCurrentVersion
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductRepository
import javax.inject.Inject

class GetProductWithCurrentVersionByIdUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
) : GetProductWithCurrentVersionByIdUseCase {

    override suspend fun invoke(
        productId: Long,
    ): RequestResult<GetProductWithCurrentVersionByIdResult> {
        return runCatching {
            val product = productRepository
                .getProductById(productId = productId)
                .getOrThrow()
                ?: return@runCatching GetProductWithCurrentVersionByIdResult.NotFound

            val currentVersion = productRepository
                .getCurrentVersion(productId = product.id)
                .getOrThrow()
                ?: return@runCatching GetProductWithCurrentVersionByIdResult.ProductWithoutCurrentVersion(
                    product = product,
                )

            GetProductWithCurrentVersionByIdResult.Found(
                product = ProductWithCurrentVersion(
                    product = product,
                    currentVersion = currentVersion,
                )
            )
        }.toRequestResult()
    }
}
