package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency

interface UpdateCurrencyUseCase {
    suspend operator fun invoke(currency: AppCurrency)
}
