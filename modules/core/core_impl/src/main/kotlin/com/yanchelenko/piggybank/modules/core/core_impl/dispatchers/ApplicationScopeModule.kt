package com.yanchelenko.piggybank.modules.core.core_impl.dispatchers

import com.yanchelenko.piggybank.modules.core.core_api.dispatchers.AppDispatchers
import com.yanchelenko.piggybank.modules.core.core_api.dispatchers.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApplicationScopeModule {

    @Provides
    @Singleton
    @ApplicationScope
    fun provideApplicationScope(
        dispatchers: AppDispatchers
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatchers.main)
}
