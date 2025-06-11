package com.yanchelenko.piggybank.scanner.di

import com.yanchelenko.piggybank.features.scanner.presentation.utils.BarcodeAnalyzer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BarcodeAnalyzerEntryPoint {
    fun barcodeAnalyzer(): BarcodeAnalyzer
}
