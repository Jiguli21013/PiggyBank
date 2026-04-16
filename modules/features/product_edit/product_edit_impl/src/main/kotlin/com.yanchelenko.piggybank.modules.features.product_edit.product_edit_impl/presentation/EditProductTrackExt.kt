package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation

import com.yanchelenko.piggybank.modules.base.ui_model.mappers.trackMap
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductState

fun EditProductState.trackMap(): Map<String, Any?> {
    return scannedProduct.trackMap() + mapOf(
        "previousPrice" to previousPrice,
        "previousWeight" to previousWeight,
        "priceInput" to priceInput,
        "hasPriceChanged" to hasPriceChanged,
        "hasWeightChanged" to hasWeightChanged,
        "hasAnyTrackedChanges" to hasAnyTrackedChanges,
    )
}
