package com.yanchelenko.piggybank.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.core.core_factory.logger.LoggerEntryPoint
import com.yanchelenko.piggybank.modules.core.core_factory.navigation.FeatureEntryPoint
import dagger.hilt.android.EntryPointAccessors

@Composable
fun hiltFeatureEntries(context: Context): Set<FeatureEntry> {
    val appContext = remember(context) { context.applicationContext }

    val entryPoint = remember(appContext) {
        EntryPointAccessors.fromApplication(
            appContext,
            FeatureEntryPoint::class.java
        )
    }

    // кэшируем результат, чтобы Hilt не лазил повторно в граф
    return remember(entryPoint) { entryPoint.featureEntries() }
}

@Composable
fun hiltLogger(context: Context): Logger {
    // Берём EntryPoint один раз — безопасно для Compose
    val entryPoint = remember(context.applicationContext) {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            LoggerEntryPoint::class.java
        )
    }

    // Кешируем сам Logger, чтобы не пересоздавался на каждой recomposition
    return remember(entryPoint) { entryPoint.logger() }
}
