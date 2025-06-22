package com.yanchelenko.piggybank.features.product_edit.presentation.state

import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel

sealed class EditProductEvent { //todo почему не sealed interface?
    data class LoadProductByProductId(val productId: Long) : EditProductEvent()
    data class ProductFoundInDB(val product: ProductUiModel) : EditProductEvent()

    data class ProductNameChanged(val name: String) : EditProductEvent()
    data class WeightChanged(val weight: Double) : EditProductEvent()
    data class PriceChanged(val price: Double) : EditProductEvent()

    data object SaveProduct : EditProductEvent()
    data object GoBackToScanner : EditProductEvent()
}
