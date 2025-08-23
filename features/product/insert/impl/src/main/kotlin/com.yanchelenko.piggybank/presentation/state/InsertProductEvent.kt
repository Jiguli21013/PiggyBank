package com.yanchelenko.piggybank.presentation.state

import com.yanchelenko.piggybank.models.Product

sealed class InsertProductEvent { //todo почему не sealed interface?
    data class LoadProductByBarcode(val barcode: String) : InsertProductEvent()
    data class ProductFoundInDB(val product: Product) : InsertProductEvent()
    data class ProductNotFoundInDB(val barcode: String) : InsertProductEvent()

    data class ProductNameChanged(val name: String) : InsertProductEvent()
    data class WeightChanged(val weight: Double) : InsertProductEvent()
    data class PriceChanged(val price: Double) : InsertProductEvent()
    data class CurrencyChanged(val currency: String) : InsertProductEvent()

    data object SaveProduct : InsertProductEvent()
    data object GoBackToScanner : InsertProductEvent()

}
