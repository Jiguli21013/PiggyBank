package com.yanchelenko.piggybank

import com.yanchelenko.piggybank.repositories.ProductsRepositoryImpl
import com.yanchelenko.piggybank.repository.ProductsRepository
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
