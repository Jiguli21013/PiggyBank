package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase

import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.infrastructure.result.toRequestResult
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductInCart
import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import javax.inject.Inject

class GetProductInActiveCartUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(barcode: String): RequestResult<ProductInCart> =
        cartRepository.getProductInActiveCartByBarcode(barcode = barcode).toRequestResult()
}
