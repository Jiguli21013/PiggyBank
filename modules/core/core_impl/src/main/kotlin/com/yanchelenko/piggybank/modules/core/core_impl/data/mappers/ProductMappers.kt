package com.yanchelenko.piggybank.modules.core.core_impl.data.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.core.database.models.ProductDBO
import kotlinx.datetime.Instant

fun ProductDBO.toScannedProduct(): ScannedProduct =
    ScannedProduct(
        id = id,
        barcode = barcode,
        productName = productName,
        weight = weight,
        price = price,
        pricePerKg = pricePerKg,
        addedAt = Instant.fromEpochMilliseconds(epochMilliseconds = addedAt)
    )

fun ScannedProduct.toProductDbo(autoGenerateId: Boolean = false): ProductDBO =
    ProductDBO(
        id = if (autoGenerateId) 0L else id,
        barcode = barcode,
        productName = productName,
        weight = weight,
        price = price,
        pricePerKg = pricePerKg,
        addedAt = addedAt.toEpochMilliseconds()
    )
