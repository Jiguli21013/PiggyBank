package com.yanchelenko.piggybank.presentation.state

sealed interface ScannerEvent {
    data class OnBarcodeScanned(val barcode: String) : ScannerEvent
    data object OnCameraPermissionMissing : ScannerEvent

    data object CheckCameraPermission : ScannerEvent
    data class OnCameraPermissionResult(val granted: Boolean) : ScannerEvent
    data object OnCameraSettingsClicked : ScannerEvent
}
