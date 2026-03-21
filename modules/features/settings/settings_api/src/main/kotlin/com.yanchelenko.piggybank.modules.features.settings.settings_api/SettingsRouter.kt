package com.yanchelenko.piggybank.modules.features.settings.settings_api

import com.yanchelenko.piggybank.modules.core.core_api.navigation.CommonRouter

interface SettingsRouter : CommonRouter {
    fun navigateToSettings()
}
