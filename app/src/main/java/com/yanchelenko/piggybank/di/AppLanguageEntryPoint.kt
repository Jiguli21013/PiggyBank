package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.AppLanguageManager
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.ObserveAppLanguageUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppLanguageEntryPoint {
    fun observeAppLanguageUseCase(): ObserveAppLanguageUseCase
    fun appLanguageManager(): AppLanguageManager
}
