package com.yanchelenko.piggybank.modules.core.core_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.ObserveAppLanguageUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.ObserveAppThemeUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.UpdateAppLanguageUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.UpdateAppThemeUseCase
import com.yanchelenko.piggybank.modules.core.core_impl.domain.settings.ObserveAppLanguageUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.settings.ObserveAppThemeUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.settings.UpdateAppLanguageUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.settings.UpdateAppThemeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsUseCaseModule {

    @Binds
    abstract fun bindObserveAppThemeUseCase(
        impl: ObserveAppThemeUseCaseImpl
    ): ObserveAppThemeUseCase

    @Binds
    abstract fun bindObserveAppLanguageUseCase(
        impl: ObserveAppLanguageUseCaseImpl
    ): ObserveAppLanguageUseCase

    @Binds
    abstract fun bindUpdateAppThemeUseCase(
        impl: UpdateAppThemeUseCaseImpl
    ): UpdateAppThemeUseCase

    @Binds
    abstract fun bindUpdateAppLanguageUseCase(
        impl: UpdateAppLanguageUseCaseImpl
    ): UpdateAppLanguageUseCase
}
