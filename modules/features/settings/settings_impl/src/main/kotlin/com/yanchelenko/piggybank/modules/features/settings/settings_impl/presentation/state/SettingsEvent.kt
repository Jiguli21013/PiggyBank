package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme

sealed interface SettingsEvent {
    data class OnThemeSelected(val theme: AppTheme) : SettingsEvent
    data class OnLanguageSelected(val language: AppLanguage) : SettingsEvent
}
