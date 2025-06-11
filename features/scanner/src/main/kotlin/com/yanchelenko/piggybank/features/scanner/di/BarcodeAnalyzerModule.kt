package com.yanchelenko.piggybank.scanner.di

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.yanchelenko.piggybank.features.scanner.domain.Scanner
import com.yanchelenko.piggybank.features.scanner.presentation.utils.BarcodeAnalyzer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BarcodeAnalyzerModule {

    @Provides
    fun provideBarcodeAnalyzer(
        scanner: Scanner,
        barcodeScanner: BarcodeScanner,
        @ApplicationContext context: Context
    ): BarcodeAnalyzer {
        return BarcodeAnalyzer(
            scanner = scanner,
            barcodeScanner = barcodeScanner,
            executor = ContextCompat.getMainExecutor(context)
        )
    }
}
