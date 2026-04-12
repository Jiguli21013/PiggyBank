package com.yanchelenko.piggybank.modules.core.core_api.repository

import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun observeCurrency(): Flow<AppCurrency>

    suspend fun setCurrency(currency: AppCurrency)
}
