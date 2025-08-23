package com.yanchelenko.piggybank

import com.yanchelenko.piggybank.domain.DeleteProductUseCase
import com.yanchelenko.piggybank.domain.DeleteProductUseCaseImpl
import com.yanchelenko.piggybank.domain.GetPricePerKgUseCase
import com.yanchelenko.piggybank.domain.GetPricePerKgUseCaseImpl
import com.yanchelenko.piggybank.domain.GetProductByIdUseCase
import com.yanchelenko.piggybank.domain.GetProductByIdUseCaseImpl
import com.yanchelenko.piggybank.domain.UpdateProductUseCase
import com.yanchelenko.piggybank.domain.UpdateProductUseCaseImpl
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
