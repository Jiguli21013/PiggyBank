package com.yanchelenko.piggybank.modules.core.core_factory

import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductFromCartUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteScannedProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetActiveCartTotalsUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetPricePerKgUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.UpdateProductUseCase
import com.yanchelenko.piggybank.modules.core.core_impl.domain.DeleteProductFromCartUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.DeleteScannedProductUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetActiveCartTotalsUseCaseImpl
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
        impl: DeleteScannedProductUseCaseImpl
    ): DeleteScannedProductUseCase

    @Binds
    @Singleton
    fun bindGetPricePerKgUseCase(
        impl: GetPricePerKgUseCaseImpl
    ): GetPricePerKgUseCase

    @Binds
    fun bindGetActiveCartTotalsUseCase(
        impl: GetActiveCartTotalsUseCaseImpl
    ): GetActiveCartTotalsUseCase

    @Binds
    fun bindDeleteProductFromCartUseCase(
        impl: DeleteProductFromCartUseCaseImpl
    ): DeleteProductFromCartUseCase

}
