package com.yanchelenko.piggybank.domain

import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.result.RequestResult

interface DeleteProductUseCase {
    suspend operator fun invoke(product: Product): RequestResult<Unit>
}
