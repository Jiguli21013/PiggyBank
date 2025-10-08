package com.yanchelenko.piggybank.modules.core.core_factory.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.core.core_impl.navigation.NavigationDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigationDispatcher(
        impl: NavigationDispatcherImpl
    ): NavigationDispatcher
}
