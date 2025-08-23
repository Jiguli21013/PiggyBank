package com.yanchelenko.piggybank.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.yanchelenko.piggybank.logger.Logger
import com.yanchelenko.piggybank.logger.LoggerEntryPoint
import com.yanchelenko.piggybank.navigation.FeatureEntry
import com.yanchelenko.piggybank.navigation.FeatureEntryPoint
import dagger.hilt.android.EntryPointAccessors

@Composable
fun hiltFeatureEntries(context: Context): Set<FeatureEntry> {
    val entryPoint = EntryPointAccessors.fromApplication(
        context = context,
        entryPoint = FeatureEntryPoint::class.java
    )
    return remember(entryPoint) { entryPoint.featureEntries() }
}

// Я вот не понял, что это? Почему не Timber?
@Composable
fun hiltLogger(context: Context): Logger {
    val entryPoint = EntryPointAccessors.fromApplication(
        context = context,
        entryPoint = LoggerEntryPoint::class.java
    )
    return remember(entryPoint) { entryPoint.logger() }
}
