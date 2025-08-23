package com.yanchelenko.piggybank.vision

import com.google.mlkit.vision.barcode.common.Barcode
import com.yanchelenko.piggybank.domain.Scanner
import javax.inject.Inject

class ScannerImpl @Inject constructor() : Scanner {
    override fun analyzeBarcodes(
        barcodes: List<Barcode>,
        onResult: (Barcode) -> Unit,
        onNotFound: () -> Unit
    ) {
        barcodes.firstOrNull()?.let(onResult) ?: onNotFound()
    }
}
