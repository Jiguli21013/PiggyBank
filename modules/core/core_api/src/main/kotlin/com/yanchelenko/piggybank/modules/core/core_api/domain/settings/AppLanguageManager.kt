package com.yanchelenko.piggybank.modules.core.core_api.domain.settings

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage

interface AppLanguageManager {
    fun apply(language: AppLanguage)
}
