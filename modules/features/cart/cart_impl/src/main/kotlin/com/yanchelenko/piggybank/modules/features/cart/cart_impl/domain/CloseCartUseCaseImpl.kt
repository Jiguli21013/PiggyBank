package com.yanchelenko.piggybank.modules.features.cart.cart_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import javax.inject.Inject

class CloseCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(totalItems: Int, totalPrice: Double): Result<Boolean> =
        repository.closeActiveCart(
            totalItems = totalItems,
            totalPrice = totalPrice
        )
}
