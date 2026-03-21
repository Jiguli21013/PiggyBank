package com.yanchelenko.piggybank.modules.features.settings.settings_impl.di

import com.yanchelenko.piggybank.modules.features.settings.settings_api.SettingsRouter
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.navigation.SettingsRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsRouteModule {

    @Binds
    abstract fun bindSettingsRouter(
        impl: SettingsRouterImpl
    ): SettingsRouter
}
