package com.yanchelenko.piggybank.modules.base.ui_model.mappers

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart

fun ProductOfCart.toUi(currency: AppCurrency): ProductOfCartUiModel {

    val formattedUnit = "$unitPrice ${currency.symbol}"
    val formattedPerKg = if (isWeightImportant) formattedUnit else null

    val weightText = when {
        isWeightImportant && weightGrams != null -> weightGrams.toString()
        else -> "—"
    }

    val totalPrice = unitPrice * quantity

    val totalPriceText = "$totalPrice ${currency.symbol}"

    return ProductOfCartUiModel(
        cartProductId = cartItemId,
        scannedProductId = productId,
        name = name,
        barcode = barcode,
        formattedPrice = formattedUnit,
        formattedPricePerKg = formattedPerKg,
        quantityText = quantity.toString(),
        weightText = weightText,
        unitPrice = formattedUnit,
        totalPriceText = totalPriceText
    )
}
