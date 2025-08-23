package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.navigation.FeatureEntry
import com.yanchelenko.piggybank.navigation.HistoryEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryEntryModule {

    @Binds
    @IntoSet
    abstract fun provideHistoryEntry(entry: HistoryEntryImpl): FeatureEntry
}
