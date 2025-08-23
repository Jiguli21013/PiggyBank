package com.yanchelenko.piggybank.domain

interface GetPricePerKgUseCase {
    operator fun invoke(weightGrams: Double, price: Double): Double
}
