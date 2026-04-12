package com.yanchelenko.piggybank.modules.core.core_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.domain.ObserveCurrencyUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.UpdateCurrencyUseCase
import com.yanchelenko.piggybank.modules.core.core_impl.domain.ObserveCurrencyUseCaseImpl
import com.yanchelenko.piggybank.modules.core.core_impl.domain.UpdateCurrencyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyUseCaseModule {

    @Binds
    fun bindObserveCurrencyUseCase(
        impl: ObserveCurrencyUseCaseImpl
    ): ObserveCurrencyUseCase

    @Binds
    fun bindUpdateCurrencyUseCase(
        impl: UpdateCurrencyUseCaseImpl
    ): UpdateCurrencyUseCase
}
