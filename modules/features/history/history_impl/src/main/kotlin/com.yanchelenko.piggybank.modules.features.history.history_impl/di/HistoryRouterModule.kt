package com.yanchelenko.piggybank.modules.features.history.history_impl.di

import com.yanchelenko.piggybank.modules.features.history.history_api.HistoryRouter
import com.yanchelenko.piggybank.modules.features.history.history_impl.navigation.HistoryRouterImpl
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
