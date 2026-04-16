package com.yanchelenko.piggybank.modules.core.core_impl.data.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductVersion
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductWithCurrentVersion
import com.yanchelenko.piggybank.modules.core.database.models.ProductWithCurrentVersionProjection
import kotlinx.datetime.Instant

fun ProductWithCurrentVersionProjection.toDomain(): ProductWithCurrentVersion {
    return ProductWithCurrentVersion(
        product = Product(
            id = productId,
            barcode = barcode,
            productName = productName,
            createdAt = Instant.fromEpochMilliseconds(productCreatedAtEpochMs),
        ),
        currentVersion = ProductVersion(
            id = versionId,
            productId = versionProductId,
            weightGrams = weightGrams,
            price = price,
            pricePerKg = pricePerKg,
            createdAt = Instant.fromEpochMilliseconds(versionCreatedAtEpochMs),
            isCurrent = isCurrent,
        )
    )
}