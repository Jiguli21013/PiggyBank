package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.navigation.ProductInsertEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductInsertEntryModule {

    @Binds
    @IntoSet
    abstract fun provideProductInsertEntry(entry: ProductInsertEntryImpl): FeatureEntry
}
