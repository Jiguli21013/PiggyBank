package com.yanchelenko.piggybank.modules.core.core_impl.data.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.ProductVersion
import com.yanchelenko.piggybank.modules.core.database.models.ProductVersionDBO
import kotlinx.datetime.Instant

fun ProductVersionDBO.toDomain(): ProductVersion {
    return ProductVersion(
        id = id,
        productId = productId,
        weightGrams = weightGrams,
        price = price,
        pricePerKg = pricePerKg,
        createdAt = Instant.fromEpochMilliseconds(createdAtEpochMs),
        isCurrent = isCurrent,
    )
}

fun ProductVersion.toDbo(): ProductVersionDBO {
    return ProductVersionDBO(
        id = id,
        productId = productId,
        weightGrams = weightGrams,
        price = price,
        pricePerKg = pricePerKg,
        createdAtEpochMs = createdAt.toEpochMilliseconds(),
        isCurrent = isCurrent,
    )
}
