package com.yanchelenko.piggybank.features.product_edit.presentation.mappers

import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductUiState
import kotlinx.datetime.Instant

fun EditProductUiState.toDomain(): Product {
    return Product(
        id = this.uiProduct.productId,
        barcode = this.uiProduct.barcode,
        productName = this.uiProduct.productName,
        weight = this.uiProduct.weight,
        price = this.uiProduct.price,
        pricePerKg = this.uiProduct.pricePerKg,
        addedAt = Instant.DISTANT_PAST // или Instant.fromEpochMilliseconds(0) временная значение, в useCase присваиваем актуальное
    )
}
