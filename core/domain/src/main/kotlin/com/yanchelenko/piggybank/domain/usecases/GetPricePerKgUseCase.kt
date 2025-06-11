package com.yanchelenko.piggybank.domain.usecases

import com.yanchelenko.piggybank.common.extensions.roundTo
import javax.inject.Inject

private const val GRAMS_IN_KG = 1000.0

class GetPricePerKgUseCase @Inject constructor() {

    operator fun invoke(weightGrams: Double, price: Double): Double {
        if (weightGrams <= 0.0) return 0.0

        val pricePerKg = (price / weightGrams) * GRAMS_IN_KG
        return pricePerKg.roundTo(2)
    }
}
