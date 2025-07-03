package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.navigation.ScannerEntryImpl
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

