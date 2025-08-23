package com.yanchelenko.piggybank.di

import com.yanchelenko.piggybank.navigation.FeatureEntry
import com.yanchelenko.piggybank.navigation.ProductDetailsEntryImpl
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

