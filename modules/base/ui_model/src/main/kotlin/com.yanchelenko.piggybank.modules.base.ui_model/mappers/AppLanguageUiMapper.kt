package com.yanchelenko.piggybank.modules.base.ui_model.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage

fun AppLanguage.toDisplayName(): String = when (this) {
    AppLanguage.SYSTEM -> "System"
    AppLanguage.ENGLISH -> "English"
    AppLanguage.RUSSIAN -> "Русский"
}
