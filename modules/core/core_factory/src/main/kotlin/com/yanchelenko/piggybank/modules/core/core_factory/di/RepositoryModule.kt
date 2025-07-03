package com.yanchelenko.piggybank.modules.core.core_factory.di

import com.yanchelenko.piggybank.modules.core.core_impl.data.repositories.ProductsRepositoryImpl
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductsRepository(
        impl: ProductsRepositoryImpl
    ): ProductsRepository
}
