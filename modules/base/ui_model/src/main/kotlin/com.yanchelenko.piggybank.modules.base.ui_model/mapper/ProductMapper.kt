package com.yanchelenko.piggybank.modules.base.ui_model.mapper

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.toInstant
import com.yanchelenko.piggybank.modules.base.ui_model.models.toStable
import com.yanchelenko.piggybank.modules.core.core_api.models.Product

fun Product.toUi(): ProductUiModel = ProductUiModel(
    productId = this.id,
    barcode = this.barcode,
    productName = this.productName,
    weight = this.weight,
    price = this.price,
    pricePerKg = this.pricePerKg,
    addedAt = this.addedAt.toStable()
)

fun ProductUiModel.toDomain(): Product = Product(
    id = this.productId,
    barcode = this.barcode,
    productName = this.productName,
    weight = this.weight,
    price = this.price,
    pricePerKg = this.pricePerKg,
    addedAt = this.addedAt.toInstant()
)
