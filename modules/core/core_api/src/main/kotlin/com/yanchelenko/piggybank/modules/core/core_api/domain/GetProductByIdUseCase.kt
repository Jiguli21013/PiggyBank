package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.core.core_api.models.Product

interface GetProductByIdUseCase {
    suspend operator fun invoke(productId: Long): RequestResult<Product>
}
