package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.HistoryRouter
import com.yanchelenko.piggybank.navigation.HistoryRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryRouterModule {

    @Binds
    abstract fun bindHistoryRouter(
        impl: HistoryRouterImpl
    ): HistoryRouter
}
