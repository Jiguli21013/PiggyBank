package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
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
