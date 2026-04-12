package com.yanchelenko.piggybank.modules.core.core_impl.data.repositories

import com.yanchelenko.piggybank.modules.core.core_api.SettingsStorage
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val settingsStorage: SettingsStorage
) : CurrencyRepository {

    override fun observeCurrency(): Flow<AppCurrency> {
        return settingsStorage.observeAppCurrency()
    }

    override suspend fun setCurrency(currency: AppCurrency) {
        settingsStorage.updateAppCurrency(currency)
    }
}
