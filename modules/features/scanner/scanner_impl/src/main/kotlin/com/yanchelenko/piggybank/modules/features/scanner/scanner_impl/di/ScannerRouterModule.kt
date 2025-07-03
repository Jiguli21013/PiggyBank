package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.di

import com.yanchelenko.piggybank.modules.features.scanner.scanner_api.ScannerRouter
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.navigation.ScannerRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScannerRouterModule {

    @Binds
    abstract fun bindScannerRouter(
        impl: ScannerRouterImpl
    ): ScannerRouter
}
