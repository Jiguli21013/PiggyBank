package com.yanchelenko.piggybank.modules.core.core_api.domain.settings

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import kotlinx.coroutines.flow.Flow

interface ObserveAppThemeUseCase {
    operator fun invoke(): Flow<AppTheme>
}
