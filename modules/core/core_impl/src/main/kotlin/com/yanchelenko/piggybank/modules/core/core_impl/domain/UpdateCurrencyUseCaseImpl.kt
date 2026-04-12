package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.domain.UpdateCurrencyUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.repository.CurrencyRepository
import javax.inject.Inject

class UpdateCurrencyUseCaseImpl @Inject constructor(
    private val repository: CurrencyRepository
) : UpdateCurrencyUseCase {

    override suspend fun invoke(currency: AppCurrency) {
        repository.setCurrency(currency)
    }
}
