package com.yanchelenko.piggybank.modules.features.settings.settings_impl.data

import com.yanchelenko.piggybank.modules.core.core_api.SettingsStorage
import com.yanchelenko.piggybank.modules.core.core_api.dispatchers.AppDispatchers
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import com.yanchelenko.piggybank.modules.core.core_api.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: SettingsStorage,
    private val dispatchers: AppDispatchers
) : SettingsRepository {

    override fun observeTheme(): Flow<AppTheme> {
        return dataStore.observeTheme()
            .map { stored ->
                stored.toAppTheme()
            }
    }

    override fun observeLanguage(): Flow<AppLanguage> {
        return dataStore.observeLanguage()
            .map { stored ->
                stored.toAppLanguage()
            }
    }

    override fun observeAppCurrency(): Flow<AppCurrency> {
        return dataStore.observeAppCurrency()
    }

    override suspend fun updateTheme(theme: AppTheme) {
        withContext(dispatchers.io) {
            dataStore.setTheme(theme.name)
        }
    }

    override suspend fun updateLanguage(language: AppLanguage) {
        withContext(dispatchers.io) {
            dataStore.setLanguage(language.name)
        }
    }

    override suspend fun updateAppCurrency(currency: AppCurrency) {
        dataStore.updateAppCurrency(currency)
    }

}
