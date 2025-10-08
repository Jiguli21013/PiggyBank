package com.yanchelenko.piggybank.modules.core.core_factory.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * EntryPoint для доступа к зарегистрированным FeatureEntry из Hilt.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface FeatureEntryPoint {
    fun featureEntries(): Set<@JvmSuppressWildcards FeatureEntry>
}
