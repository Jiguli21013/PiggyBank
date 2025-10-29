package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.navigation.HistoryOfCartsEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryOfCartsEntryModule {

    @Binds
    @IntoSet
    abstract fun provideHistoryOfCartsEntry(entry: HistoryOfCartsEntryImpl): FeatureEntry
}
