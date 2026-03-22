package com.yanchelenko.piggybank.modules.core.core_impl.dispatchers

import com.yanchelenko.piggybank.modules.core.core_api.dispatchers.AppDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DispatchersModule {

    @Binds
    fun bindAppDispatchers(
        impl: AppDispatchersImpl
    ): AppDispatchers
}
