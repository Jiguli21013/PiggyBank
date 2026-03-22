package com.yanchelenko.piggybank.modules.features.settings.settings_impl.domain

import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.ObserveAppLanguageUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppLanguageUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ObserveAppLanguageUseCase {

    override fun invoke(): Flow<AppLanguage> {
        return settingsRepository.observeLanguage()
    }
}
