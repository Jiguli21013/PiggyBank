package com.yanchelenko.piggybank.scanner.di

import com.yanchelenko.piggybank.scanner.data.MlKitScanner
import com.yanchelenko.piggybank.features.scanner.domain.Scanner
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
