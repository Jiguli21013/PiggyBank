package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.ProductDetailsRouter
import com.yanchelenko.piggybank.navigation.ProductDetailsRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductDetailsRouterModule {

    @Binds
    abstract fun bindProductDetailsRouter(
        impl: ProductDetailsRouterImpl
    ): ProductDetailsRouter
}
