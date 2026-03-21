package com.yanchelenko.piggybank.modules.core.core_api.domain.settings

import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import kotlinx.coroutines.flow.Flow

interface ObserveAppLanguageUseCase {
    operator fun invoke(): Flow<AppLanguage>
}
