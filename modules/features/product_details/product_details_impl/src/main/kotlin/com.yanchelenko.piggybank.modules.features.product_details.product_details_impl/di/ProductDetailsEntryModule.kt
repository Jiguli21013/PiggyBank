package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.navigation.ProductDetailsEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductDetailsEntryModule {

    @Binds
    @IntoSet
    abstract fun bindProductDetailsEntry(entry: ProductDetailsEntryImpl): FeatureEntry
}

