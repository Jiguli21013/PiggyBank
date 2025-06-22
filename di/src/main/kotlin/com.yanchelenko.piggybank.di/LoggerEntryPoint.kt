package com.yanchelenko.piggybank.di

import com.yanchelneko.piggybank.common.core_utils.Logger
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LoggerEntryPoint {
    fun logger(): Logger
}
