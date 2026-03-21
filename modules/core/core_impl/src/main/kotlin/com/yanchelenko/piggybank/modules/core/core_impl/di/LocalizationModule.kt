package com.yanchelenko.piggybank.modules.core.core_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.AppLanguageManager
import com.yanchelenko.piggybank.modules.core.core_impl.presentation.AppLanguageManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalizationModule {

    @Binds
    abstract fun bindAppLanguageManager(
        impl: AppLanguageManagerImpl
    ): AppLanguageManager
}
