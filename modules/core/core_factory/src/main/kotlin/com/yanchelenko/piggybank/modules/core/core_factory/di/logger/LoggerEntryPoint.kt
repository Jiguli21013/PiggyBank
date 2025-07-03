package com.yanchelenko.piggybank.modules.core.core_factory.di.logger

import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LoggerEntryPoint {
    fun logger(): Logger
}
