package com.yanchelenko.piggybank.logger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Provides
    fun provideLogger(): Logger = androidLogcatLogger()
}
