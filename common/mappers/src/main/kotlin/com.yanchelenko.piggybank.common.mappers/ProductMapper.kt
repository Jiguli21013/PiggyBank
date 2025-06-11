package com.yanchelenko.piggybank.common.mappers

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import com.yanchelenko.piggybank.domain.models.Product

fun Product.toUi(): ProductUiModel = ProductUiModel(
    productId = this.id,
    barcode = this.barcode,
    productName = this.productName,
    weight = this.weight,
    price = this.price,
    pricePerKg = this.pricePerKg,
    addedAt = this.addedAt
)

fun ProductUiModel.toDomain(): Product = Product(
    id = this.productId,
    barcode = this.barcode,
    productName = this.productName,
    weight = this.weight,
    price = this.price,
    pricePerKg = this.pricePerKg,
    addedAt = this.addedAt
)
