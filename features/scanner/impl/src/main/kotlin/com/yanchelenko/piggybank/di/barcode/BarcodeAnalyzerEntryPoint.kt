package com.yanchelenko.piggybank.di.barcode

import com.yanchelenko.piggybank.vision.BarcodeAnalyzer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BarcodeAnalyzerEntryPoint {
    fun barcodeAnalyzer(): BarcodeAnalyzer
}
