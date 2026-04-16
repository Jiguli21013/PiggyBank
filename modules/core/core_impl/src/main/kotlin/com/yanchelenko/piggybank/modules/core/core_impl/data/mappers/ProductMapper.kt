package com.yanchelenko.piggybank.modules.core.core_impl.data.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.database.models.ProductDBO
import kotlinx.datetime.Instant

fun ProductDBO.toDomain(): Product {
    return Product(
        id = id,
        barcode = barcode,
        productName = productName,
        createdAt = Instant.fromEpochMilliseconds(createdAtEpochMs),
    )
}

fun Product.toDbo(): ProductDBO {
    return ProductDBO(
        id = id,
        barcode = barcode,
        productName = productName,
        createdAtEpochMs = createdAt.toEpochMilliseconds(),
    )
}
