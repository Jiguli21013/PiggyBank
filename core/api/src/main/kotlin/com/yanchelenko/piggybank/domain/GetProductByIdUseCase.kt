package com.yanchelenko.piggybank.domain

import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.result.RequestResult

interface GetProductByIdUseCase {
    suspend operator fun invoke(productId: Long): RequestResult<Product>
}
