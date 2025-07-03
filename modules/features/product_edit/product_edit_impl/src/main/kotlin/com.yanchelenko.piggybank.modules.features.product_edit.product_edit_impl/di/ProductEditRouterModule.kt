package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.di

import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_api.ProductEditRouter
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.navigation.ProductEditRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductEditRouterModule {

    @Binds
    abstract fun bindProductEditRouter(
        impl: ProductEditRouterImpl
    ): ProductEditRouter
}
