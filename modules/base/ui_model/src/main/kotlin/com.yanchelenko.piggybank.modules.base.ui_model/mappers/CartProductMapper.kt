package com.yanchelenko.piggybank.modules.base.ui_model.mappers

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import java.text.NumberFormat
import java.util.Locale

fun ProductOfCart.toUi(): ProductOfCartUiModel {
    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault())

    val formattedUnit = currencyFormatter.format(unitPrice)
    val formattedPerKg = if (isWeightImportant) formattedUnit else null

    val weightText = when {
        isWeightImportant && weightGrams != null -> "$weightGrams" //todo
        else -> "—"
    }

    // compute total correctly
    val totalPrice = if (isWeightImportant) {
        unitPrice * ((weightGrams ?: 0) / 1000.0) * quantity //todo to const
    } else {
        unitPrice * quantity
    }

    val totalPriceText = currencyFormatter.format(totalPrice)

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
