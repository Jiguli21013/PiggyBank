package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation

import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toUi
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.toStable
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase.ProductInitResultDomain
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductState
import kotlinx.datetime.Clock

/**
 * Maps [ProductInitResultDomain] (domain DTO) into [InsertProductState] (UI state).
 * Used in ViewModel after initializing InsertProductScreen.
 */
fun ProductInitResultDomain.toUiState(): InsertProductState {
    val scannedProductUi = product?.toUi() ?: run {
        // fallback UI object if product not found in DB
        ScannedProductUiModel(
            productId = 0,
            barcode = barcode,
            productName = "",
            weight = 0,
            price = 0.0,
            pricePerKg = 0.0,
            addedAt = Clock.System.now().toStable()
        )
    }

    return InsertProductState(
        scannedProduct = scannedProductUi,
        quantity = quantityInCart.coerceAtLeast(1),
        cartItemId = cartItemId,
        isInCart = isInCart,
        priceInput = if (scannedProductUi.price > 0.0) scannedProductUi.price.toString() else ""
    )
}
