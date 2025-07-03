package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.base.infrastructure.extensions.roundTo
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetPricePerKgUseCase
import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import javax.inject.Inject

class GetPricePerKgUseCaseImpl @Inject constructor(
    private val logger: Logger
) : GetPricePerKgUseCase {
    override operator fun invoke(weightGrams: Double, price: Double): Double {
        if (weightGrams <= 0.0) {
            logger.e(LOG_TAG, "Invalid weight: $weightGrams. Returning 0.0")
            return 0.0
        }

        val pricePerKg = (price / weightGrams) * GRAMS_PER_KILOGRAM
        val rounded = pricePerKg.roundTo(DECIMAL_PRECISION)

        logger.d(
            LOG_TAG,
            "Calculated pricePerKg: raw=$pricePerKg, rounded=$rounded for weight=$weightGrams, price=$price"
        )

        return rounded
    }

    companion object {
        private const val LOG_TAG = "GetPricePerKgUseCaseImpl"

        private const val GRAMS_PER_KILOGRAM = 1000.0
        private const val DECIMAL_PRECISION = 2
    }
}
