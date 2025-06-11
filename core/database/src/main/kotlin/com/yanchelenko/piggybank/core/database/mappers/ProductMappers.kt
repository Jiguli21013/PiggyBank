package com.yanchelenko.piggybank.core.database.mappers

import com.yanchelenko.piggybank.core.database.models.ProductDBO
import com.yanchelenko.piggybank.domain.models.Product
import kotlinx.datetime.Instant

fun ProductDBO.toProduct(): Product =
    Product(
        id = id,
        barcode = barcode,
        productName = productName,
        weight = weight,
        price = price,
        pricePerKg = pricePerKg,
        addedAt = Instant.fromEpochMilliseconds(epochMilliseconds = addedAt)
    )

fun Product.toProductDbo(autoGenerateId: Boolean = false): ProductDBO =
    ProductDBO(
        id = if (autoGenerateId) 0L else id,
        barcode = barcode,
        productName = productName,
        weight = weight,
        price = price,
        pricePerKg = pricePerKg,
        addedAt = addedAt.toEpochMilliseconds()
    )
