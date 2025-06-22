package com.yanchelenko.piggybank.domain.usecases

import com.yanchelenko.piggybank.common.extensions.roundTo
import com.yanchelneko.piggybank.common.core_utils.Logger
import javax.inject.Inject

class GetPricePerKgUseCase @Inject constructor(
    private val logger: Logger
) {
    operator fun invoke(weightGrams: Double, price: Double): Double {
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
        private const val LOG_TAG = "GetPricePerKgUseCase"

        private const val GRAMS_PER_KILOGRAM = 1000.0
        private const val DECIMAL_PRECISION = 2
    }
}
