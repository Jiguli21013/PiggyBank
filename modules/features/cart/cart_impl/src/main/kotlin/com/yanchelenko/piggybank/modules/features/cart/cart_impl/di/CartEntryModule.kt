package com.yanchelenko.piggybank.modules.features.cart.cart_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.navigation.CartEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class CartEntryModule {
    @Binds
    @IntoSet
    abstract fun provideCartEntry(entry: CartEntryImpl): FeatureEntry
}
