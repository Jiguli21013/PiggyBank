package com.yanchelenko.piggybank.modules.core_impl

import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toScannedProduct
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toScannedProductDbo
import com.yanchelenko.piggybank.modules.core.database.models.ScannedProductDBO
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScannedProductMapperTest {

    @Test
    fun `ProductDBO toDomain preserves fields`() {
        val now = Instant.parse("2025-10-08T13:00:00Z")
        val dbo = ScannedProductDBO(
            id = 1,
            barcode = "123456",
            productName = "Banana",
            weight = 250.0,
            price = 1.25,
            pricePerKg = 5.0,
            addedAt = now.toEpochMilliseconds()
        )

        val domain = dbo.toScannedProduct()

        assertEquals(dbo.id, domain.id)
        assertEquals(dbo.barcode, domain.barcode)
        assertEquals(dbo.productName, domain.productName)
        assertEquals(dbo.weight, domain.weight)
        assertEquals(dbo.price, domain.price)
        assertEquals(dbo.pricePerKg, domain.pricePerKg)
        assertEquals(now, domain.addedAt)
    }

    @Test
    fun `Product toDBO converts Instant to millis`() {
        val now = Instant.parse("2025-10-08T13:00:00Z")
        val scannedProduct = ScannedProduct(
            id = 42L,
            barcode = "987654",
            productName = "Apple",
            weight = 333.0,
            price = 2.0,
            pricePerKg = 6.0,
            addedAt = now
        )

        val dbo = scannedProduct.toScannedProductDbo()

        assertEquals(scannedProduct.id, dbo.id)
        assertEquals(scannedProduct.barcode, dbo.barcode)
        assertEquals(scannedProduct.productName, dbo.productName)
        assertEquals(scannedProduct.weight, dbo.weight)
        assertEquals(scannedProduct.price, dbo.price)
        assertEquals(scannedProduct.pricePerKg, dbo.pricePerKg)
        assertEquals(now.toEpochMilliseconds(), dbo.addedAt)
    }
}
