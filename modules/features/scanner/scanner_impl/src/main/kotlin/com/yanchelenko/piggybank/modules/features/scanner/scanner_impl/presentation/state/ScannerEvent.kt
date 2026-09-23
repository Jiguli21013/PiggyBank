package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state

import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.ScannedBarcode

sealed interface ScannerEvent {
    data class OnBarcodeScanned(val barcode: ScannedBarcode) : ScannerEvent
    data object OnCameraPermissionMissing : ScannerEvent

    data object CheckCameraPermission : ScannerEvent
    data class OnCameraPermissionResult(val granted: Boolean) : ScannerEvent
    data object OnCameraSettingsClicked : ScannerEvent
}
