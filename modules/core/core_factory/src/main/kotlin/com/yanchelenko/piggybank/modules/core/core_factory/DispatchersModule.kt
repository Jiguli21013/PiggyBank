package com.yanchelenko.piggybank.modules.core.core_factory

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    /*
    @Provides
    fun provideDispatchersProvider(): AppDispatchers.Provider = DefaultDispatchersProvider()

    @Provides
    fun provideAppDispatchers(provider: AppDispatchers.Provider): AppDispatchers =
        AppDispatchers(provider = provider)

     */
}
