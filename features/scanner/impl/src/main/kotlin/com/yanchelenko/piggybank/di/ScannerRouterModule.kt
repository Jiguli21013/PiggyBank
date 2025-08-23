package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.ScannerRouter
import com.yanchelenko.piggybank.navigation.ScannerRouterImpl
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
