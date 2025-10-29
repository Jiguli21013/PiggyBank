package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state

import androidx.compose.runtime.Immutable
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.trackMap
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

@Immutable
data class InsertProductState(
    val scannedProduct: ScannedProductUiModel = ScannedProductUiModel(barcode = ""),

    val cartItemId: Long? = null, // id of cart item // if null then item not in the cart
    val quantity: Int = 0,
    val isInCart: Boolean = false,

    val priceInput: String = "",
    val isWeightNotImportant: Boolean = false,
)

fun InsertProductState.trackMap(): Map<String, Any?> {
    val productMap = scannedProduct.trackMap()
    return productMap + mapOf(
        "quantity" to quantity,
        "isInCart" to isInCart,
        "cartItemId" to cartItemId
    )
}
