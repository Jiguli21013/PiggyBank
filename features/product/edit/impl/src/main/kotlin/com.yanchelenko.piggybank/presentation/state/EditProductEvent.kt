package com.yanchelenko.piggybank.presentation.state

import com.yanchelenko.piggybank.models.Product

sealed class EditProductEvent { //todo почему не sealed interface?
    data class LoadProductByProductId(val productId: Long) : EditProductEvent()
    data class ProductFoundInDB(val product: Product) : EditProductEvent()

    data class ProductNameChanged(val name: String) : EditProductEvent()
    data class WeightChanged(val weight: Double) : EditProductEvent()
    data class PriceChanged(val price: Double) : EditProductEvent()

    data object SaveProduct : EditProductEvent()
    data object GoBackToScanner : EditProductEvent()
}
