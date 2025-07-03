package com.yanchelenko.piggybank.modules.core.core_factory.di

import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetPricePerKgUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.UpdateProductUseCase
import com.yanchelenko.piggybank.modules.core.core_impl.domain.DeleteProductUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetPricePerKgUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetProductByIdUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.UpdateProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreBindsModule {

    @Binds
    fun bindGetProductByIdUseCase(
        impl: GetProductByIdUseCaseImpl
    ): GetProductByIdUseCase

    @Binds
    fun bindUpdateProductUseCase(
        impl: UpdateProductUseCaseImpl
    ): UpdateProductUseCase

    @Binds
    @Singleton
    fun bindDeleteProductUseCase(
        impl: DeleteProductUseCaseImpl
    ): DeleteProductUseCase

    @Binds
    @Singleton
    fun bindGetPricePerKgUseCase(
        impl: GetPricePerKgUseCaseImpl
    ): GetPricePerKgUseCase

}
