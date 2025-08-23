package com.yanchelenko.piggybank.di.barcode

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yanchelenko.piggybank.vision.BarcodeAnalyzer
import dagger.hilt.android.EntryPointAccessors

@Composable
fun hiltBarcodeAnalyzer(): BarcodeAnalyzer {
    val context = LocalContext.current
    val entryPoint = EntryPointAccessors.fromApplication(
        context,
        BarcodeAnalyzerEntryPoint::class.java
    )
    return entryPoint.barcodeAnalyzer()
}
