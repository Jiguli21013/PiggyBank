package com.yanchelenko.piggybank.modules.features.settings.settings_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.UpdateAppThemeUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import com.yanchelenko.piggybank.modules.core.core_api.repository.SettingsRepository
import javax.inject.Inject

class UpdateAppThemeUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UpdateAppThemeUseCase {

    override suspend fun invoke(theme: AppTheme) {
        settingsRepository.updateTheme(theme)
    }
}
