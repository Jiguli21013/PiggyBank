package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

sealed interface EditProductEvent {
    data class LoadProductByProductId(val productId: Long) : EditProductEvent
    data class ProductFoundInDB(val product: ScannedProductUiModel) : EditProductEvent

    data class ProductNameChanged(val name: String) : EditProductEvent
    data class WeightChanged(val weight: Int) : EditProductEvent
    data class PriceInputChanged(val input: String) : EditProductEvent
    data class PriceChanged(val price: Double) : EditProductEvent

    data object SaveProduct : EditProductEvent
    data object GoBackToScanner : EditProductEvent
}
