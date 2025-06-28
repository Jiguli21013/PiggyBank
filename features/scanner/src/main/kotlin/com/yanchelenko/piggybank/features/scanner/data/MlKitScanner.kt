package com.yanchelenko.piggybank.scanner.data

import com.google.mlkit.vision.barcode.common.Barcode
import com.yanchelenko.piggybank.features.scanner.domain.Scanner
import javax.inject.Inject

// nit: ну так то Scanner уже использует MlKit, предлагаю нейминг interface MlKitScanner -> class MlKitScannerImpl
class MlKitScanner @Inject constructor() : Scanner {
    override fun analyzeBarcodes(
        barcodes: List<Barcode>,
        onResult: (Barcode) -> Unit,
        onNotFound: () -> Unit
    ) {
        if (!barcodes.isNullOrEmpty()) {
            onResult(barcodes.first())
        } else {
            onNotFound()
        }
    }
}
