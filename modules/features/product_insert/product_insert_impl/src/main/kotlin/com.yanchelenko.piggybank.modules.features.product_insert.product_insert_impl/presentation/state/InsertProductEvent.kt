package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state

sealed interface InsertProductEvent {

    data class LoadProductByBarcode(val barcode: String) : InsertProductEvent
    data class ProductFoundInDB(val state: InsertProductState) : InsertProductEvent
    data class ProductNotFoundInDB(val state: InsertProductState) : InsertProductEvent

    data class ProductNameChanged(val name: String) : InsertProductEvent
    data class WeightChanged(val weight: Int) : InsertProductEvent

    data class PriceChanged(val price: Double) : InsertProductEvent
    data class PriceInputChanged(val input: String) : InsertProductEvent

    data class CurrencyChanged(val currency: String) : InsertProductEvent

    data class WeightNotImportantChanged(val isChecked: Boolean) : InsertProductEvent

    data object DecreaseQuantity : InsertProductEvent
    data object IncreaseQuantity : InsertProductEvent

    data object AddToCart : InsertProductEvent
    data object RemoveFromCart : InsertProductEvent
    data object SaveProduct : InsertProductEvent
    data object GoBackToScanner : InsertProductEvent
}
