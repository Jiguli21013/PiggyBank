package com.yanchelenko.piggybank.modules.base.ui_model.mappers

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import java.text.NumberFormat
import java.util.Locale

//todo перепроверить мапперы
fun ProductOfCart.toUi(): ProductOfCartUiModel {
    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault())

    val formattedUnit = currencyFormatter.format(unitPrice)
    val formattedPerKg = if (isWeightBased) formattedUnit else null

    val weightText = if (isWeightBased && weightGrams != null) "$weightGrams" else "Не указано" //todo

    return ProductOfCartUiModel(
        cartItemId = cartItemId,
        productId = productId ?: 0,
        name = name,
        barcode = barcode,
        formattedPrice = formattedUnit,
        formattedPricePerKg = formattedPerKg,
        quantityText = quantity.toString(),
        weightText = weightText,
        unitPrice = this.unitPrice.toString(), //todo norm?
        totalPriceText = (unitPrice * quantity).toString()
    )
}
