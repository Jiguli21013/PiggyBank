package com.yanchelenko.piggybank.modules.features.settings.settings_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.navigation.SettingsEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsEntryModule {

    @Binds
    @IntoSet
    abstract fun bindSettingsEntry(entry: SettingsEntryImpl): FeatureEntry
}

