package com.yanchelenko.piggybank.modules.core.core_api.domain.settings

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme

interface UpdateAppThemeUseCase {
    suspend operator fun invoke(theme: AppTheme)
}
