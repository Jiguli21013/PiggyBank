package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult

/**
 * Contract for removing a product from the active cart.
 */
interface DeleteProductFromCartUseCase {
    suspend operator fun invoke(productOfCartId: Long): RequestResult<Long>
}
