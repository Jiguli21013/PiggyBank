package com.yanchelenko.piggybank.models

//todo только для дебаг сборок, сделать аннотацию
fun Product.trackMap(): Map<String, Any?> {
    return mapOf(
        "barcode" to barcode,
        "productName" to productName,
        "weight" to weight,
        "price" to price,
        "pricePerKg" to pricePerKg
    )
}
