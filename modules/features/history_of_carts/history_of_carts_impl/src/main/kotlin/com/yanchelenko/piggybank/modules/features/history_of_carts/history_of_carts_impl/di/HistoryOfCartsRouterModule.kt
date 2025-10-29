package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.di

import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_api.HistoryOfCartsRouter
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.navigation.HistoryOfCartsRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryOfCartsRouterModule {

    @Binds
    abstract fun bindHistoryRouter(
        impl: HistoryOfCartsRouterImpl
    ): HistoryOfCartsRouter
}
