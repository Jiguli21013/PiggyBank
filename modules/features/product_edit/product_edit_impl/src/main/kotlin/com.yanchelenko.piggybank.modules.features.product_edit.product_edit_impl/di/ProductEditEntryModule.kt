package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.navigation.ProductEditEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductEditEntryModule {

    @Binds
    @IntoSet
    abstract fun provideProductEditEntry(entry: ProductEditEntryImpl): FeatureEntry
}
