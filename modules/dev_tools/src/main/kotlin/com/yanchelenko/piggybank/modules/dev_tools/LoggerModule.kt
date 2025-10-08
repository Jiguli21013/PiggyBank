package com.yanchelenko.piggybank.modules.dev_tools

import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.dev_tools.logger.AndroidLogger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoggerModule {
    @Binds
    @Singleton
    abstract fun bindLogger(impl: AndroidLogger): Logger
}