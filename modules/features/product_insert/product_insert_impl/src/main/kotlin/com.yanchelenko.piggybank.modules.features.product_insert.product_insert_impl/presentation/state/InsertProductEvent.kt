package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel

sealed interface InsertProductEvent {

    data class LoadProductByBarcode(val barcode: String) : InsertProductEvent
    data class ProductFoundInDB(val product: ProductUiModel) : InsertProductEvent
    data class ProductNotFoundInDB(val product: ProductUiModel) : InsertProductEvent

    data class ProductNameChanged(val name: String) : InsertProductEvent
    data class WeightChanged(val weight: Double) : InsertProductEvent
    data class PriceChanged(val price: Double) : InsertProductEvent
    data class CurrencyChanged(val currency: String) : InsertProductEvent

    data object SaveProduct : InsertProductEvent
    data object GoBackToScanner : InsertProductEvent
}
