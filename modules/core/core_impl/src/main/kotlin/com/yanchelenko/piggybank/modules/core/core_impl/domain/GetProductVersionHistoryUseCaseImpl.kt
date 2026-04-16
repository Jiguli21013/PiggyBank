package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductVersionHistoryUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductVersion
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductRepository
import javax.inject.Inject

class GetProductVersionHistoryUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
) : GetProductVersionHistoryUseCase {

    override suspend fun invoke(productId: Long): RequestResult<List<ProductVersion>> {
        return productRepository
            .getVersionHistory(productId = productId)
            .toRequestResult()
    }
}
