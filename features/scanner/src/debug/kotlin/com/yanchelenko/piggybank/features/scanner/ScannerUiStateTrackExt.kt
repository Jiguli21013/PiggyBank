package com.yanchelenko.piggybank.features.scanner

import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerUiState

fun ScannerUiState.trackMap(): Map<String, Any?> {
    return mapOf(
        "scannerState" to scannerState,
        "barcode" to barcode,
        "boundingRect" to boundingRect,
        "cameraGranted" to cameraPermissionState.granted,
        "showSettings" to cameraPermissionState.showSettings,
        "cameraRequested" to cameraPermissionState.requested
    )
}
