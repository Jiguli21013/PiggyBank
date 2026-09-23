package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state

import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.domain.BarcodeValidationError

sealed interface ScannerEffect {
    data class NavigateToInsertProduct(val barcode: String) : ScannerEffect
    data class ShowError(val error: BarcodeValidationError) : ScannerEffect


    data object RequestSystemCameraPermission : ScannerEffect
    data object OpenCameraSettings : ScannerEffect
}
