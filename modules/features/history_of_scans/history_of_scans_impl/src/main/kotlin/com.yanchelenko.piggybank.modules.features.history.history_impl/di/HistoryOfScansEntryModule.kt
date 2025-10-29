package com.yanchelenko.piggybank.modules.features.history.history_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.history.history_impl.navigation.HistoryOfScansEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryOfScansEntryModule {

    @Binds
    @IntoSet
    abstract fun provideHistoryOfScansEntry(entry: HistoryOfScansEntryImpl): FeatureEntry
}
