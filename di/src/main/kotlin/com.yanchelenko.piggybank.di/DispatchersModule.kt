package com.yanchelenko.piggybank.di

import com.yanchelneko.piggybank.common.core_utils.dispatchers.AppDispatchers
import com.yanchelneko.piggybank.common.core_utils.dispatchers.DefaultDispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    fun provideDispatchersProvider(): AppDispatchers.Provider = DefaultDispatchersProvider()

    @Provides
    fun provideAppDispatchers(provider: AppDispatchers.Provider): AppDispatchers =
        AppDispatchers(provider)
}
