package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state

sealed class SettingsEffect {
    data class ShowMessage(val message: String) : SettingsEffect()
}
