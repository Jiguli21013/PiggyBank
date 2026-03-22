package com.yanchelenko.piggybank.modules.features.settings.settings_impl.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "settings"

val Context.settingsDataStore by preferencesDataStore(DATASTORE_NAME)

class SettingsDataStore(private val context: Context) {

    private val dataStore = context.settingsDataStore

    private object Keys {
        val THEME = stringPreferencesKey("app_theme")
        val LANGUAGE = stringPreferencesKey("app_language")
    }

    fun observeTheme(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[Keys.THEME]
        }
    }

    fun observeLanguage(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[Keys.LANGUAGE]
        }
    }

    suspend fun setTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[Keys.THEME] = theme
        }
    }

    suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[Keys.LANGUAGE] = language
        }
    }
}
