package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcherImpl
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
