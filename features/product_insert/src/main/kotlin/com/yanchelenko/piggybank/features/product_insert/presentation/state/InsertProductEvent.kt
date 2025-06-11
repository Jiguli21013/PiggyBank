package com.yanchelenko.piggybank.features.product_insert.presentation.state

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel

sealed class InsertProductEvent { //todo почему не sealed interface?
    data class LoadProductByBarcode(val barcode: String) : InsertProductEvent()
    data class ProductFoundInDB(val product: ProductUiModel) : InsertProductEvent()

    data class ProductNameChanged(val name: String) : InsertProductEvent()
    data class WeightChanged(val weight: Double) : InsertProductEvent()
    data class PriceChanged(val price: Double) : InsertProductEvent()
    data class CurrencyChanged(val currency: String) : InsertProductEvent()

    data object SaveProduct : InsertProductEvent()
    data object GoBackToScanner : InsertProductEvent()

}
