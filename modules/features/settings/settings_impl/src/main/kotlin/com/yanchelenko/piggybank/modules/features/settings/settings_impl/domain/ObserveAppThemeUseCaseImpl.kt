package com.yanchelenko.piggybank.modules.features.settings.settings_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.ObserveAppThemeUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import com.yanchelenko.piggybank.modules.core.core_api.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppThemeUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ObserveAppThemeUseCase {

    override fun invoke(): Flow<AppTheme> {
        return settingsRepository.observeTheme()
    }
}
