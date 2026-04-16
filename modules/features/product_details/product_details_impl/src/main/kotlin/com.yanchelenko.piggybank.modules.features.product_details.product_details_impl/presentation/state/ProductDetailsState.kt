package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

data class ProductDetailsState(
    val product: ScannedProductUiModel = ScannedProductUiModel(barcode = ""),
    val previousPrice: Double? = null,
    val previousWeight: Int? = null,
    val hasPriceChanged: Boolean = false,
    val hasWeightChanged: Boolean = false,
) {
    val hasAnyChanges: Boolean
        get() = hasPriceChanged || hasWeightChanged

    val priceDelta: Double?
        get() = previousPrice?.let { product.price - it }

    val weightDelta: Int?
        get() = previousWeight?.let { product.weight - it }
}
