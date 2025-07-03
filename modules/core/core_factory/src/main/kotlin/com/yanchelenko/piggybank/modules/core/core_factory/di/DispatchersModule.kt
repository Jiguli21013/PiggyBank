package com.yanchelenko.piggybank.modules.core.core_factory.di

import com.yanchelenko.piggybank.modules.base.infrastructure.dispatchers.AppDispatchers
import com.yanchelenko.piggybank.modules.base.infrastructure.dispatchers.DefaultDispatchersProvider
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
