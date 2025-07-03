package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.di

import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_api.ProductInsertRouter
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.navigation.ProductInsertRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductInsertRouterModule {

    @Binds
    abstract fun bindProductInsertRouter(
        impl: ProductInsertRouterImpl
    ): ProductInsertRouter
}
