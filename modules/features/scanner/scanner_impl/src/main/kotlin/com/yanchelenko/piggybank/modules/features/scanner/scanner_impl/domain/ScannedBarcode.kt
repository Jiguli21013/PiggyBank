package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain

data class ScannedBarcode(val value: String, val format: BarcodeFormat)

enum class BarcodeFormat {
    EAN_8, EAN_13, UPC_A, UPC_E, UNSUPPORTED
}
