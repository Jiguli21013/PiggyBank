package com.yanchelenko.piggybank.modules.core.core_impl.data.repositories

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import com.yanchelenko.piggybank.modules.core.core_api.repository.SettingsRepository
import com.yanchelenko.piggybank.modules.core.core_impl.data.SettingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
//todo может все таки в модуль settings переместить
class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: SettingsDataStore
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

    override suspend fun updateTheme(theme: AppTheme) {
        dataStore.setTheme(theme.name)
    }

    override suspend fun updateLanguage(language: AppLanguage) {
        dataStore.setLanguage(language.name)
    }
}

/**
 * Mapping String -> Enum
 */
//todo move
private fun String?.toAppTheme(): AppTheme {
    return when (this) {
        AppTheme.LIGHT.name -> AppTheme.LIGHT
        AppTheme.DARK.name -> AppTheme.DARK
        AppTheme.SYSTEM.name, null -> AppTheme.SYSTEM
        else -> AppTheme.SYSTEM
    }
}

private fun String?.toAppLanguage(): AppLanguage {
    return when (this) {
        AppLanguage.ENGLISH.name -> AppLanguage.ENGLISH
        AppLanguage.RUSSIAN.name -> AppLanguage.RUSSIAN
        AppLanguage.SYSTEM.name, null -> AppLanguage.SYSTEM
        else -> AppLanguage.SYSTEM
    }
}
