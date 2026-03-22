package com.yanchelenko.piggybank.modules.features.settings.settings_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.UpdateAppLanguageUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.repository.SettingsRepository
import javax.inject.Inject


class UpdateAppLanguageUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UpdateAppLanguageUseCase {

    override suspend fun invoke(language: AppLanguage) {
        settingsRepository.updateLanguage(language)
    }
}
