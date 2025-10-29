package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.core.core_api.models.CartTotals
import kotlinx.coroutines.flow.Flow

/**
 * Domain use case that exposes live totals for the active cart.
 * Emits updates whenever cart items change.
 */
interface GetActiveCartTotalsUseCase {
    operator fun invoke(): Flow<CartTotals>
}
