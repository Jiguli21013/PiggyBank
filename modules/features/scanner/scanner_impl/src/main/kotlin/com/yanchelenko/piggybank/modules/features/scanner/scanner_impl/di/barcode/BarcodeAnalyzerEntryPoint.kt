package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.di.barcode

import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision.BarcodeAnalyzer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BarcodeAnalyzerEntryPoint {
    fun barcodeAnalyzer(): BarcodeAnalyzer
}
