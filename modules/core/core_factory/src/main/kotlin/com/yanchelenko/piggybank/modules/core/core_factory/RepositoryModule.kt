package com.yanchelenko.piggybank.modules.core.core_factory

import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import com.yanchelenko.piggybank.modules.core.core_impl.data.repositories.ScannedProductsRepositoryImpl
import com.yanchelenko.piggybank.modules.core.core_api.repository.ScannedProductsRepository
import com.yanchelenko.piggybank.modules.core.core_impl.data.repositories.CartRepositoryImpl
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
        impl: ScannedProductsRepositoryImpl
    ): ScannedProductsRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository
}
