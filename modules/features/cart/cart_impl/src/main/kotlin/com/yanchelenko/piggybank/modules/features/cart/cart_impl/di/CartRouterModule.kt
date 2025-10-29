package com.yanchelenko.piggybank.modules.features.cart.cart_impl.di

import com.yanchelenko.piggybank.modules.features.cart.cart_api.CartRouter
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.navigation.CartRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CartRouterModule {
    @Binds
    abstract fun bindCartRouter(
        impl: CartRouterImpl
    ): CartRouter
}
