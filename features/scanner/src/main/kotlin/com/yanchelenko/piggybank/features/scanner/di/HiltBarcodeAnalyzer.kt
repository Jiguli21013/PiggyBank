package com.yanchelenko.piggybank.features.scanner.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yanchelenko.piggybank.scanner.di.BarcodeAnalyzerEntryPoint
import com.yanchelenko.piggybank.features.scanner.presentation.utils.BarcodeAnalyzer
import dagger.hilt.android.EntryPointAccessors
//todo то ли место ?
@Composable
fun hiltBarcodeAnalyzer(): BarcodeAnalyzer =
    EntryPointAccessors
        .fromApplication(
            context = LocalContext.current,
            entryPoint = BarcodeAnalyzerEntryPoint::class.java
        ).barcodeAnalyzer()
