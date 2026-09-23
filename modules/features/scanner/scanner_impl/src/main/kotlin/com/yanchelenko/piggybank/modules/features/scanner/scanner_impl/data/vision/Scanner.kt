package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision

import com.google.mlkit.vision.barcode.common.Barcode

interface Scanner {
    fun analyzeBarcodes(
        barcodes: List<Barcode>,
        onResult: (Barcode) -> Unit,
        onNotFound: () -> Unit
    )
}
