package com.yanchelenko.piggybank.modules.core.core_factory.di.logger

import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import com.yanchelenko.piggybank.modules.core.core_impl.logger.androidLogcatLogger
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
