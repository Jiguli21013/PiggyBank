package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain

import javax.inject.Inject

enum class BarcodeValidationError {
    UNSUPPORTED_FORMAT, INVALID_VALUE
}

/** Checks supported retail barcode structure, not whether a product exists in a catalogue. */
class ValidateProductBarcodeUseCase @Inject constructor() {
    operator fun invoke(barcode: ScannedBarcode): BarcodeValidationError? {
        val expectedLength = when (barcode.format) {
            BarcodeFormat.EAN_8, BarcodeFormat.UPC_E -> 8
            BarcodeFormat.EAN_13 -> 13
            BarcodeFormat.UPC_A -> 12
            BarcodeFormat.UNSUPPORTED -> return BarcodeValidationError.UNSUPPORTED_FORMAT
        }
        val value = barcode.value
        if (value.length != expectedLength || value.any { it !in '0'..'9' }) {
            return BarcodeValidationError.INVALID_VALUE
        }

        val gtin = if (barcode.format == BarcodeFormat.UPC_E) {
            if (value.first() !in '0'..'1') return BarcodeValidationError.INVALID_VALUE
            expandUpcE(value)
        } else {
            value
        }
        val weightedSum = gtin.dropLast(1).reversed().mapIndexed { index, digit ->
            (digit - '0') * if (index % 2 == 0) 3 else 1
        }.sum()
        val checkDigit = (10 - weightedSum % 10) % 10
        return if (checkDigit == gtin.last() - '0') null else BarcodeValidationError.INVALID_VALUE
    }

    // UPC-E's check digit belongs to the expanded UPC-A number, not the eight printed digits.
    private fun expandUpcE(value: String): String {
        val payload = value.substring(1, 7)
        val expanded = when (payload.last()) {
            '0', '1', '2' -> payload.take(2) + payload.last() + "0000" + payload.substring(2, 5)
            '3' -> payload.take(3) + "00000" + payload.substring(3, 5)
            '4' -> payload.take(4) + "00000" + payload[4]
            else -> payload.take(5) + "0000" + payload.last()
        }
        return value.first() + expanded + value.last()
    }
}
