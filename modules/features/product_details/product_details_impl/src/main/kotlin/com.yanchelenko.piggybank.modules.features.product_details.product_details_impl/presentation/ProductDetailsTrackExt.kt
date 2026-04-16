package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation

import com.yanchelenko.piggybank.modules.base.ui_model.mappers.trackMap
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsState

fun ProductDetailsState.trackMap(): Map<String, Any?> {
    return product.trackMap() + mapOf(
        "previousPrice" to previousPrice,
        "previousWeight" to previousWeight,
        "hasPriceChanged" to hasPriceChanged,
        "hasWeightChanged" to hasWeightChanged,
        "hasAnyChanges" to hasAnyChanges,
        "priceDelta" to priceDelta,
        "weightDelta" to weightDelta,
    )
}
