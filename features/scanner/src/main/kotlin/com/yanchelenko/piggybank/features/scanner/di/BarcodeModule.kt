package com.yanchelenko.piggybank.features.scanner.di

import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BarcodeModule {

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
