package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state

sealed interface ScannerEffect {
    data class NavigateToInsertProduct(val barcode: String) : ScannerEffect
    data class ShowError(val message: String) : ScannerEffect


    data object RequestSystemCameraPermission : ScannerEffect
    data object OpenCameraSettings : ScannerEffect
}
