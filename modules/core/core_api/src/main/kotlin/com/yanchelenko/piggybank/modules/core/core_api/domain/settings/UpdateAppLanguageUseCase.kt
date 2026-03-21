package com.yanchelenko.piggybank.modules.core.core_api.domain.settings

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage

interface UpdateAppLanguageUseCase {
    suspend operator fun invoke(language: AppLanguage)
}
