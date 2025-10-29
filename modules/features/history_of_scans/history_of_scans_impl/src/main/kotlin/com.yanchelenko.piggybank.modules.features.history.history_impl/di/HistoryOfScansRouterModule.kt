package com.yanchelenko.piggybank.modules.features.history.history_impl.di

import com.yanchelenko.piggybank.modules.features.history.history_api.HistoryOfScansRouter
import com.yanchelenko.piggybank.modules.features.history.history_impl.navigation.HistoryOfScansRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryOfScansRouterModule {

    @Binds
    abstract fun bindHistoryRouter(
        impl: HistoryOfScansRouterImpl
    ): HistoryOfScansRouter
}
