package com.yanchelenko.piggybank.modules.core.core_api.domain

import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import kotlinx.coroutines.flow.Flow

interface ObserveCurrencyUseCase {
    operator fun invoke(): Flow<AppCurrency>
}
