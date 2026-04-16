package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductVersion

interface GetProductVersionHistoryUseCase {
    suspend operator fun invoke(productId: Long): RequestResult<List<ProductVersion>>
}
