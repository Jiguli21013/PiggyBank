package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision

import android.graphics.Rect
import com.google.mlkit.vision.barcode.common.Barcode
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.BarcodeFormat
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.ScannedBarcode

data class DetectedBarcode(val barcode: ScannedBarcode, val boundingBox: Rect?)

internal fun Barcode.toDetectedBarcode(): DetectedBarcode = DetectedBarcode(
    barcode = ScannedBarcode(
        value = rawValue.orEmpty(),
        format = when (format) {
            Barcode.FORMAT_EAN_8 -> BarcodeFormat.EAN_8
            Barcode.FORMAT_EAN_13 -> BarcodeFormat.EAN_13
            Barcode.FORMAT_UPC_A -> BarcodeFormat.UPC_A
            Barcode.FORMAT_UPC_E -> BarcodeFormat.UPC_E
            else -> BarcodeFormat.UNSUPPORTED
        },
    ),
    boundingBox = boundingBox,
)
