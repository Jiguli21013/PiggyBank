package com.yanchelenko.piggybank.modules.base.ui_model.mappers

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.toInstant
import com.yanchelenko.piggybank.modules.base.ui_model.models.toStable
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct

fun ScannedProduct.toUi(): ScannedProductUiModel = ScannedProductUiModel(
    productId = this.id,
    barcode = this.barcode,
    productName = this.productName,
    weight = this.weight,
    price = this.price,
    pricePerKg = this.pricePerKg,
    addedAt = this.addedAt.toStable()
)

fun ScannedProductUiModel.toDomain(): ScannedProduct = ScannedProduct(
    id = this.productId,
    barcode = this.barcode,
    productName = this.productName,
    weight = this.weight,
    price = this.price,
    pricePerKg = this.pricePerKg,
    addedAt = this.addedAt.toInstant()
)

fun ScannedProductUiModel.toCartDomain(quantity: Int): ProductOfCart = ProductOfCart(
    cartItemId = this.productId, // todo
    productId = this.productId, //todo лишнее кажись
    barcode = this.barcode,
    name = this.productName,
    unitPrice = this.price,
    isWeightBased = true,
    weightGrams = this.weight.toInt(),
    quantity = quantity
)
