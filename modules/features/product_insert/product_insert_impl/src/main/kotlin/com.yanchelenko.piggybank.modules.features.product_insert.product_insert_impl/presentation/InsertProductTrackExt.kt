package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation

import com.yanchelenko.piggybank.modules.base.ui_model.mappers.trackMap
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductState

fun InsertProductState.trackMap(): Map<String, Any?> {
    val productMap = scannedProduct.trackMap()
    return productMap + mapOf(
        "quantity" to quantity,
        "isInCart" to isInCart,
        "cartItemId" to cartItemId,
        "previousPrice" to previousPrice,
        "previousWeight" to previousWeight,
        "hasPriceChanged" to hasPriceChanged,
        "hasWeightChanged" to hasWeightChanged,
    )
}
