package com.yanchelenko.piggybank.modules.base.ui_model.mappers

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

//todo только для дебаг сборок, сделать аннотацию
fun ScannedProductUiModel.trackMap(): Map<String, Any?> {
    return mapOf(
        "barcode" to barcode,
        "productName" to productName,
        "weight" to weight,
        "price" to price,
        "pricePerKg" to pricePerKg
    )
}
