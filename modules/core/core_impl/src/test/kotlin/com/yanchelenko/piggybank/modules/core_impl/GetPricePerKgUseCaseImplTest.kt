package com.yanchelenko.piggybank.modules.core_impl

import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetPricePerKgUseCaseImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import io.mockk.mockk
import io.mockk.verify

class GetPricePerKgUseCaseImplTest {

    private val logger = mockk<Logger>(relaxed = true)
    private val useCase = GetPricePerKgUseCaseImpl(logger)

    @Test
    fun `returns 0_0 when weight is zero`() {
        // arrange
        val weight = 0.0
        val price = 100.0

        // act
        val result = useCase(weight, price)

        // assert
        assertEquals(0.0, result)
        verify { logger.e("GetPricePerKgUseCaseImpl", "Invalid weight: 0.0. Returning 0.0") }
    }

    @Test
    fun `calculates correct price per kg`() {
        // arrange
        val weightGrams = 500.0 // 0.5 кг
        val price = 25.0        // за полкило
        // ожидаем: (25 / 500) * 1000 = 50.0

        // act
        val result = useCase(weightGrams, price)

        // assert
        assertEquals(50.0, result)
        verify {
            logger.d(
                "GetPricePerKgUseCaseImpl",
                match { it.contains("rounded=50.0") }
            )
        }
    }

    @Test
    fun `rounds to 2 decimal places`() {
        // arrange
        val weightGrams = 333.3
        val price = 10.0
        // (10 / 333.3) * 1000 = 30.0030003… → округляем до 30.00

        // act
        val result = useCase(weightGrams, price)

        // assert
        assertEquals(30.0, result)
    }
}
