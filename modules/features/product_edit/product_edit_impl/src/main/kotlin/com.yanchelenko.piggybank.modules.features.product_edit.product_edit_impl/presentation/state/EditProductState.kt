package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state

import androidx.compose.runtime.Immutable
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

@Immutable
data class EditProductState(
    val scannedProduct: ScannedProductUiModel,
    val previousPrice: Double? = null,
    val previousWeight: Int? = null,
    val isInScannedDB: Boolean = true,
    val priceInput: String,
) {
    val hasPriceChanged: Boolean
        get() = previousPrice != null && previousPrice != scannedProduct.price

    val hasWeightChanged: Boolean
        get() = previousWeight != null && previousWeight != scannedProduct.weight

    val hasAnyTrackedChanges: Boolean
        get() = hasPriceChanged || hasWeightChanged
}
