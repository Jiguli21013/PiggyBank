package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.domain.Scanner
import com.yanchelenko.piggybank.vision.MlKitScanner
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
