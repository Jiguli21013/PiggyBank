package com.yanchelenko.piggybank.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.core.core_factory.di.logger.LoggerEntryPoint
import com.yanchelenko.piggybank.modules.core.core_factory.di.navigation.FeatureEntryPoint
import dagger.hilt.android.EntryPointAccessors

@Composable
fun hiltFeatureEntries(context: Context): Set<FeatureEntry> {
    val entryPoint = EntryPointAccessors.fromApplication(
        context,
        FeatureEntryPoint::class.java
    )
    return remember(entryPoint) { entryPoint.featureEntries() }
}

@Composable
fun hiltLogger(context: Context): Logger {
    val entryPoint = EntryPointAccessors.fromApplication(
        context,
        LoggerEntryPoint::class.java
    )
    return remember(entryPoint) { entryPoint.logger() }
}
