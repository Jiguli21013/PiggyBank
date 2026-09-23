package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class ValidateProductBarcodeUseCaseTest {
    private val validate = ValidateProductBarcodeUseCase()

    @Test
    fun `accepts retail formats and preserves leading zeroes`() {
        listOf(
            ScannedBarcode("96385074", BarcodeFormat.EAN_8),
            ScannedBarcode("4006381333931", BarcodeFormat.EAN_13),
            ScannedBarcode("036000291452", BarcodeFormat.UPC_A),
            ScannedBarcode("04252614", BarcodeFormat.UPC_E),
        ).forEach { assertNull(validate(it), it.toString()) }
    }

    @Test
    fun `checks UPC-E using its expanded UPC-A value`() {
        // Covers each zero-suppression branch, including final payload digits 0, 1 and 2.
        listOf("01234505", "01234514", "01234523", "01234531", "01234543", "01234558")
            .forEach { assertNull(validate(ScannedBarcode(it, BarcodeFormat.UPC_E)), it) }
    }

    @Test
    fun `rejects unsupported formats even when they contain a valid GTIN`() {
        listOf("4006381333931", "https://example.com/product/123", "hello").forEach {
            assertEquals(
                BarcodeValidationError.UNSUPPORTED_FORMAT,
                validate(ScannedBarcode(it, BarcodeFormat.UNSUPPORTED)),
            )
        }
    }

    @Test
    fun `rejects malformed values and incorrect check digits`() {
        listOf(
            ScannedBarcode("", BarcodeFormat.EAN_13),
            ScannedBarcode("123", BarcodeFormat.EAN_13),
            ScannedBarcode("4006381333932", BarcodeFormat.EAN_13),
            ScannedBarcode("400638133393 ", BarcodeFormat.EAN_13),
            ScannedBarcode("４006381333931", BarcodeFormat.EAN_13),
            ScannedBarcode("400638133393/", BarcodeFormat.EAN_13),
            ScannedBarcode("96385075", BarcodeFormat.EAN_8),
            ScannedBarcode("036000291453", BarcodeFormat.UPC_A),
            ScannedBarcode("04252615", BarcodeFormat.UPC_E),
            ScannedBarcode("24252614", BarcodeFormat.UPC_E),
            ScannedBarcode("96385074", BarcodeFormat.EAN_13),
        ).forEach {
            assertEquals(BarcodeValidationError.INVALID_VALUE, validate(it), it.toString())
        }
    }
}
