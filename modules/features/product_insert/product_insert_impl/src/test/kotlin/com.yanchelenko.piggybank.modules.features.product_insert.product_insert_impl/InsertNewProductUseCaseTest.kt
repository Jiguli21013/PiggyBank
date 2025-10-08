package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl

import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductsRepository
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase.InsertNewProductUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class InsertNewProductUseCaseTest {

    private val repository = mockk<ProductsRepository>()
    private val logger = mockk<Logger>(relaxed = true)
    private val useCase = InsertNewProductUseCase(repository, logger)

    @Test
    fun `overwrites addedAt with now and calls repository`() = runTest {
        // arrange: продукт с любым дефолтом в addedAt
        val input = Product(
            id = 0L,
            barcode = "test-barcode",
            productName = "Banana",
            price = 1.99,
            addedAt = Instant.DISTANT_PAST,
            weight = 233.3,
            pricePerKg = 12.0
        )

        val captured = slot<Product>()
        // репозиторий возвращает успешный результат (тип — kotlin.Result<Unit>)
        coEvery { repository.saveProductToDatabase(capture(captured)) } returns Result.success(Unit)

        // фиксируем границы времени вокруг вызова
        val before = Clock.System.now()

        // act
        val result = useCase(input)

        val after = Clock.System.now()

        // assert: лог попытки вставки
        coVerify { logger.d("InsertNewProductUseCase", match { it.startsWith("Inserting product:") }) }

        // assert: репозиторий вызван ровно 1 раз
        coVerify(exactly = 1) { repository.saveProductToDatabase(any()) }

        // assert: addedAt действительно перезаписан "сейчас" (в разумном интервале)
        val passed = captured.captured
        val ts = passed.addedAt
        assertTrue(ts >= before && ts <= after) { "expected addedAt in [$before..$after], but was $ts" }

        // результат мы здесь не утверждаем (маппер toRequestResult() — другая ответственность)
        @Suppress("UNUSED_VARIABLE")
        val _ignore = result
    }
}
