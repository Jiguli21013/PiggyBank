package com.yanchelenko.piggybank.features.product_insert.presentation.mappers

import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductUiState
import kotlinx.datetime.Instant

fun InsertProductUiState.toDomain(): Product {
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
