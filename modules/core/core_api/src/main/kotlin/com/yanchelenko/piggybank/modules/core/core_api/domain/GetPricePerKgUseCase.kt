package com.yanchelenko.piggybank.modules.core.core_api.domain

interface GetPricePerKgUseCase {
    operator fun invoke(weightGrams: Int, price: Double): Double
}
