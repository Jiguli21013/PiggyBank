package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.ProductInsertRouter
import com.yanchelenko.piggybank.navigation.ProductInsertRouterImpl
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
