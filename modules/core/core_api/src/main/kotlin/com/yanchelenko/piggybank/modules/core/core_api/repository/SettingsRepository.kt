package com.yanchelenko.piggybank.modules.core.core_api.repository

import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun observeTheme(): Flow<AppTheme>
    fun observeLanguage(): Flow<AppLanguage>
    fun observeAppCurrency(): Flow<AppCurrency>

    suspend fun updateTheme(theme: AppTheme)
    suspend fun updateLanguage(language: AppLanguage)
    suspend fun updateAppCurrency(currency: AppCurrency)
}
