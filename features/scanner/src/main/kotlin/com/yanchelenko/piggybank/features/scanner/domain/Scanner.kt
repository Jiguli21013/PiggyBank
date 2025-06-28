package com.yanchelenko.piggybank.features.scanner.domain

import com.google.mlkit.vision.barcode.common.Barcode

interface Scanner {
    fun analyzeBarcodes(
        barcodes: List<Barcode>,// чтобы избавиться от зависимости на mlkit надо Barcode сделать абстракцией (но это наверное лишнее)
        onResult: (Barcode) -> Unit,
        onNotFound: () -> Unit
    )
}
