package com.yanchelenko.piggybank.modules.core.core_impl.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.yanchelenko.piggybank.modules.core.core_api.SettingsStorage
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "settings"

val Context.settingsDataStore by preferencesDataStore(DATASTORE_NAME)

class SettingsStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsStorage {

    private val dataStore = context.settingsDataStore

    private object Keys {
        val THEME = stringPreferencesKey("app_theme")
        val LANGUAGE = stringPreferencesKey("app_language")
        val CURRENCY = stringPreferencesKey("app_currency")
    }

    override fun observeTheme(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[Keys.THEME]
        }
    }

    override fun observeLanguage(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[Keys.LANGUAGE]
        }
    }

    override fun observeAppCurrency(): Flow<AppCurrency> {
        return dataStore.data.map { preferences ->
            AppCurrency.fromCode(preferences[Keys.CURRENCY])
        }
    }

    override suspend fun setTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[Keys.THEME] = theme
        }
    }

    override suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[Keys.LANGUAGE] = language
        }
    }

    override suspend fun updateAppCurrency(currency: AppCurrency) {
        dataStore.edit { preferences ->
            preferences[Keys.CURRENCY] = currency.code
        }
    }

}
