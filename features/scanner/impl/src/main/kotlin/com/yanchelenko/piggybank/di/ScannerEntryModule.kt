package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.navigation.FeatureEntry
import com.yanchelenko.piggybank.navigation.ScannerEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class ScannerEntryModule {

    @Binds
    @IntoSet
    abstract fun bindScannerEntry(entry: ScannerEntryImpl): FeatureEntry
}

