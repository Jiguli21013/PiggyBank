package com.yanchelenko.piggybank

import com.yanchelenko.piggybank.dispatchers.DispatchersProvider
import com.yanchelenko.piggybank.dispatchers.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DispatchersProviderImpl()
}
