package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.toStable
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase.ProductInitResultDomain
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductState
import kotlinx.datetime.Clock
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.models.GetProductWithCurrentVersionByBarcodeResult

/**
 * Maps [ProductInitResultDomain] (domain DTO) into [InsertProductState] (UI state).
 * Used in ViewModel after initializing InsertProductScreen.
 */
fun ProductInitResultDomain.toUiState(currency: AppCurrency): InsertProductState {
    val scannedProductUi = when (val result = productResult) {
        is GetProductWithCurrentVersionByBarcodeResult.Found -> {
            val product = result.product.product
            val currentVersion = result.product.currentVersion

            ScannedProductUiModel(
                productId = product.id,
                barcode = product.barcode,
                productName = product.productName,
                weight = currentVersion.weightGrams,
                price = currentVersion.price,
                pricePerKg = currentVersion.pricePerKg,
                formattedPrice = currentVersion.price.toCurrencyText(currency = currency),
                formattedPricePerKg = currentVersion.pricePerKg.toCurrencyText(currency = currency),
                addedAt = currentVersion.createdAt.toStable(),
            )
        }

        is GetProductWithCurrentVersionByBarcodeResult.NotFound,
        is GetProductWithCurrentVersionByBarcodeResult.ProductWithoutCurrentVersion -> {
            ScannedProductUiModel(
                productId = 0,
                barcode = barcode,
                productName = "",
                weight = 0,
                price = 0.0,
                pricePerKg = 0.0,
                formattedPrice = "0 ${currency.symbol}",
                formattedPricePerKg = "0 ${currency.symbol}",
                addedAt = Clock.System.now().toStable(),
            )
        }
    }

    return InsertProductState(
        scannedProduct = scannedProductUi,
        previousPrice = when (val result = productResult) {
            is GetProductWithCurrentVersionByBarcodeResult.Found -> result.product.currentVersion.price
            is GetProductWithCurrentVersionByBarcodeResult.NotFound,
            is GetProductWithCurrentVersionByBarcodeResult.ProductWithoutCurrentVersion -> null
        },
        previousWeight = when (val result = productResult) {
            is GetProductWithCurrentVersionByBarcodeResult.Found -> result.product.currentVersion.weightGrams
            is GetProductWithCurrentVersionByBarcodeResult.NotFound,
            is GetProductWithCurrentVersionByBarcodeResult.ProductWithoutCurrentVersion -> null
        },
        quantity = quantityInCart.coerceAtLeast(1),
        cartItemId = cartItemId,
        isInCart = isInCart,
        priceInput = if (scannedProductUi.price > 0.0) scannedProductUi.price.toString() else "",
    )
}

private fun Double.toCurrencyText(currency: AppCurrency): String {
    return "$this ${currency.symbol}"
}