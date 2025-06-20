package com.yanchelenko.piggybank.core.debugUI.debug

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel

fun ProductUiModel.trackMap(): Map<String, Any?> {
    return mapOf(
        "barcode" to barcode,
        "productName" to productName,
        "weight" to weight,
        "price" to price,
        "pricePerKg" to pricePerKg
    )
}
