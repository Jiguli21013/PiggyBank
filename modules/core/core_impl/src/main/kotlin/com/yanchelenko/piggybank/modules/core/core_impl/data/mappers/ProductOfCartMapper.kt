package com.yanchelenko.piggybank.modules.core.core_impl.data.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import com.yanchelenko.piggybank.modules.core.database.models.CartItemDBO

internal fun CartItemDBO.toProductOfCart(): ProductOfCart =
    ProductOfCart(
        cartItemId = id,
        productId = productId,
        barcode = barcode,
        name = name,
        unitPrice = unitPrice,
        isWeightBased = isWeightBased,
        weightGrams = weightGrams,
        quantity = quantity
    )
