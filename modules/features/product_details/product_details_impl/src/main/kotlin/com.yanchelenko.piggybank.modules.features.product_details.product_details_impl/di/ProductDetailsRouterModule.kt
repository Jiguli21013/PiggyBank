package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.di

import com.yanchelenko.piggybank.modules.features.product_details.product_details_api.ProductDetailsRouter
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.navigation.ProductDetailsRouterImpl
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
