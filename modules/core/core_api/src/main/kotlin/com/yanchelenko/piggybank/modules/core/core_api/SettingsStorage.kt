package com.yanchelenko.piggybank.modules.core.core_api

import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import kotlinx.coroutines.flow.Flow


interface SettingsStorage {
    fun observeTheme(): Flow<String?>
    fun observeLanguage(): Flow<String?>
    fun observeAppCurrency(): Flow<AppCurrency>

    suspend fun setTheme(theme: String)
    suspend fun setLanguage(language: String)
    suspend fun updateAppCurrency(currency: AppCurrency)
}
