package com.yanchelenko.piggybank.modules.core.core_factory

import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductFromCartUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteScannedProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetActiveCartTotalsUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetPricePerKgUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductVersionHistoryUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductWithCurrentVersionByBarcodeUseCase
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetProductWithCurrentVersionByBarcodeUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductWithCurrentVersionByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetProductWithCurrentVersionByIdUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_api.domain.SaveProductVersionIfChangedUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.UpdateProductUseCase
import com.yanchelenko.piggybank.modules.core.core_impl.domain.DeleteProductFromCartUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.DeleteScannedProductUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetActiveCartTotalsUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetPricePerKgUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.GetProductVersionHistoryUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.SaveProductVersionIfChangedUseCaseImpl
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

    @Binds
    fun bindSaveProductVersionIfChangedUseCase(
        impl: SaveProductVersionIfChangedUseCaseImpl
    ): SaveProductVersionIfChangedUseCase

    @Binds
    fun bindGetProductWithCurrentVersionByBarcodeUseCase(
        impl: GetProductWithCurrentVersionByBarcodeUseCaseImpl
    ): GetProductWithCurrentVersionByBarcodeUseCase

    @Binds
    fun bindGetProductWithCurrentVersionByIdUseCase(
        impl: GetProductWithCurrentVersionByIdUseCaseImpl
    ): GetProductWithCurrentVersionByIdUseCase

    @Binds
    fun bindGetProductVersionHistoryUseCase(
        impl: GetProductVersionHistoryUseCaseImpl
    ): GetProductVersionHistoryUseCase
}
