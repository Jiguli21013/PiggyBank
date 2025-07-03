package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision

import com.google.mlkit.vision.barcode.common.Barcode
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.Scanner
import javax.inject.Inject

class MlKitScanner @Inject constructor() : Scanner {
    override fun analyzeBarcodes(
        barcodes: List<Barcode>,
        onResult: (Barcode) -> Unit,
        onNotFound: () -> Unit
    ) {
        if (barcodes.isNotEmpty()) {
            onResult(barcodes.first())
        } else {
            onNotFound()
        }
    }
}
