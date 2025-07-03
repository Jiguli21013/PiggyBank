package com.yanchelenko.piggybank.modules.base.ui_model.mapper

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
//todo только для дебаг сборок, сделать аннотацию
fun ProductUiModel.trackMap(): Map<String, Any?> {
    return mapOf(
        "barcode" to barcode,
        "productName" to productName,
        "weight" to weight,
        "price" to price,
        "pricePerKg" to pricePerKg
    )
}
