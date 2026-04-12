package com.yanchelenko.piggybank.modules.core.core_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.domain.ObserveCurrencyUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.repository.CurrencyRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveCurrencyUseCaseImpl @Inject constructor(
    private val repository: CurrencyRepository
) : ObserveCurrencyUseCase {

    override operator fun invoke(): Flow<AppCurrency> {
        return repository.observeCurrency()
    }
}
