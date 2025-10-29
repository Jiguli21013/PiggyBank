package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state

import androidx.compose.runtime.Immutable
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

@Immutable
data class EditProductState(
    val scannedProduct: ScannedProductUiModel,

    val priceInput: String,
)
