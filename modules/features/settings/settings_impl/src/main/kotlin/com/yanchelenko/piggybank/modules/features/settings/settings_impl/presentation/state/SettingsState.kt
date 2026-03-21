package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state

import androidx.compose.runtime.Immutable
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme

@Immutable
data class SettingsState(
    val selectedTheme: AppTheme = AppTheme.SYSTEM,
    val selectedLanguage: AppLanguage = AppLanguage.SYSTEM
)
