package com.yanchelenko.piggybank.modules.core.core_api.domain

interface GetPricePerKgUseCase {
    operator fun invoke(weightGrams: Double, price: Double): Double
}
