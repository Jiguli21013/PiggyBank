package com.yanchelenko.piggybank.features.scanner.domain

import com.google.mlkit.vision.barcode.common.Barcode

interface Scanner {
    fun analyzeBarcodes(
        barcodes: List<Barcode>,
        onResult: (Barcode) -> Unit,
        onNotFound: () -> Unit
    )
}
