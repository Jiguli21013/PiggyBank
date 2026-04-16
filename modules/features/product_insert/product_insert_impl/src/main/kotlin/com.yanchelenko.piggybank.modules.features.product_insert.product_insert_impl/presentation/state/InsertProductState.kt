package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state

import androidx.compose.runtime.Immutable
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

@Immutable
data class InsertProductState(
    val scannedProduct: ScannedProductUiModel = ScannedProductUiModel(barcode = ""),
    val isInScannedDB: Boolean = false,
    val previousPrice: Double? = null,
    val previousWeight: Int? = null,

    val cartItemId: Long? = null, // id of cart item // if null then item not in the cart
    val quantity: Int = 0,

    val isInCart: Boolean = false,

    val priceInput: String = "",
    val isWeightNotImportant: Boolean = false,
) {
    val hasPriceChanged: Boolean
        get() = previousPrice != null && previousPrice != scannedProduct.price

    val hasWeightChanged: Boolean
        get() = previousWeight != null && previousWeight != scannedProduct.weight

    val hasAnyTrackedChanges: Boolean
        get() = hasPriceChanged || hasWeightChanged
}

