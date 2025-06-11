package com.yanchelenko.piggybank.features.scanner.presentation.state

import com.yanchelenko.piggybank.features.scanner.presentation.models.UiError
import com.yanchelenko.piggybank.features.scanner.presentation.models.BoundingRect

data class ScannerUiState(
    val barcode: String? = null,
    val boundingRect: BoundingRect? = null,

    val scannerState: ScannerState = ScannerState.Idle,
    val error: UiError? = null,

    val cameraPermissionState: CameraPermissionState = CameraPermissionState()
)
