package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.di

import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision.MlKitScanner
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.Scanner
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ScannerModule {

    @Binds
    abstract fun bindScanner(impl: MlKitScanner): Scanner
}
