package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductFromCartUseCase
import javax.inject.Inject

/**
 * Use case for removing a product from the active cart.
 * Called when user presses "Remove from cart".
 */
class DeleteProductFromCartUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository,
    private val logger: Logger
) : DeleteProductFromCartUseCase {

    override suspend operator fun invoke(productOfCartId: Long): RequestResult<Long> {
        logger.d(LOG_TAG, "Removing product from cart: product id = $productOfCartId")
        return cartRepository.removeProductFromCart(productOfCartId = productOfCartId)
            .toRequestResult()
            .also {
                if (it is RequestResult.Error) {
                    logger.e(LOG_TAG, "Failed to remove product from cart DB: ${it.error?.message}")
                } else {
                    logger.d(LOG_TAG, "Product successfully removed from cart")
                }
            }
    }

    companion object {
        private const val LOG_TAG = "DeleteProductFromCartUseCaseImpl"
    }
}
