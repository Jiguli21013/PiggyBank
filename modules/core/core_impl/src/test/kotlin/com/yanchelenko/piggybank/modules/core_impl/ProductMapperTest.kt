package com.yanchelenko.piggybank.modules.core_impl

import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.database.mappers.toProduct
import com.yanchelenko.piggybank.modules.core.database.mappers.toProductDbo
import com.yanchelenko.piggybank.modules.core.database.models.ProductDBO
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductMapperTest {

    @Test
    fun `ProductDBO toDomain preserves fields`() {
        val now = Instant.parse("2025-10-08T13:00:00Z")
        val dbo = ProductDBO(
            id = 1,
            barcode = "123456",
            productName = "Banana",
            weight = 250.0,
            price = 1.25,
            pricePerKg = 5.0,
            addedAt = now.toEpochMilliseconds()
        )

        val domain = dbo.toProduct()

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
        val product = Product(
            id = 42L,
            barcode = "987654",
            productName = "Apple",
            weight = 333.0,
            price = 2.0,
            pricePerKg = 6.0,
            addedAt = now
        )

        val dbo = product.toProductDbo()

        assertEquals(product.id, dbo.id)
        assertEquals(product.barcode, dbo.barcode)
        assertEquals(product.productName, dbo.productName)
        assertEquals(product.weight, dbo.weight)
        assertEquals(product.price, dbo.price)
        assertEquals(product.pricePerKg, dbo.pricePerKg)
        assertEquals(now.toEpochMilliseconds(), dbo.addedAt)
    }
}
