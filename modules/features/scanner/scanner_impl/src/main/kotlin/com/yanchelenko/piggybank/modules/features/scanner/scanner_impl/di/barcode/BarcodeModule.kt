package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.di.barcode

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision.BarcodeAnalyzer
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.Scanner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BarcodeModule {

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

    @Provides
    fun provideBarcodeScanner(): BarcodeScanner {
        val supportedBarcodeFormats = listOf(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_CODABAR,
            Barcode.FORMAT_CODE_93,
            Barcode.FORMAT_CODE_39,
            Barcode.FORMAT_CODE_128,
            Barcode.FORMAT_EAN_8,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_AZTEC
        )

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                supportedBarcodeFormats.first(),
                *supportedBarcodeFormats.drop(1).toIntArray()
            )
            .build()

        return BarcodeScanning.getClient(options)
    }
}
