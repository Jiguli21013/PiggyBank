package com.yanchelenko.piggybank.modules.features.settings.settings_impl.data

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme

/**
 * Mapping String -> Enum
 */
fun String?.toAppTheme(): AppTheme {
    return when (this) {
        AppTheme.LIGHT.name -> AppTheme.LIGHT
        AppTheme.DARK.name -> AppTheme.DARK
        AppTheme.SYSTEM.name, null -> AppTheme.SYSTEM
        else -> AppTheme.SYSTEM
    }
}

fun String?.toAppLanguage(): AppLanguage {
    return when (this) {
        AppLanguage.ENGLISH.name -> AppLanguage.ENGLISH
        AppLanguage.RUSSIAN.name -> AppLanguage.RUSSIAN
        AppLanguage.SYSTEM.name, null -> AppLanguage.SYSTEM
        else -> AppLanguage.SYSTEM
    }
}
