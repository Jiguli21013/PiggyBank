package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision

import androidx.camera.core.ImageAnalysis
import androidx.camera.mlkit.vision.MlKitAnalyzer
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.common.Barcode
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.Scanner
import java.util.concurrent.Executor
import javax.inject.Inject

class BarcodeAnalyzer @Inject constructor(
    private val scanner: Scanner,
    private val barcodeScanner: BarcodeScanner,
    private val executor: Executor
) {

    fun buildAnalyzer(
        onResult: (Barcode) -> Unit,
        onNotFound: () -> Unit
    ): ImageAnalysis.Analyzer {
        return MlKitAnalyzer(
            listOf(element = barcodeScanner),
            ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
            executor
        ) { result ->
            val barcodes = result?.getValue(barcodeScanner)
            if (barcodes != null) {
                scanner.analyzeBarcodes(
                    barcodes = barcodes,
                    onResult = onResult,
                    onNotFound = onNotFound
                )
            }
        }
    }
}
