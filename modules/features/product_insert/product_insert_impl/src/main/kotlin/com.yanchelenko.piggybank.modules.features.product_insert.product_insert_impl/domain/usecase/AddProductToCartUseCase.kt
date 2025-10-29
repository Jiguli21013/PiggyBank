package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val repository: CartRepository,
    private val logger: Logger
) {
    suspend operator fun invoke(productOfCart: ProductOfCart): RequestResult<Long> {
        //val now: Instant = Clock.System.now() todo это лушче здесь в юзкейсе
        val productOfCartWithTimestamp = productOfCart.copy()
        logger.d(LOG_TAG, "Inserting product to cart: $productOfCartWithTimestamp")
        return repository.addProductToCart(productOfCart = productOfCartWithTimestamp)
            .toRequestResult()
            .also {
                if (it is RequestResult.Error) {
                    logger.e(LOG_TAG, "Insert result error: ${it.error?.message}")
                } else {
                    logger.d(LOG_TAG, "Insert result: $it")
                }
            }
    }

    private companion object {
        const val LOG_TAG = "AddProductToCartUseCase"
    }
}
