package com.yanchelenko.piggybank.presentation.state

import com.yanchelenko.piggybank.presentation.model.BoundingRect


data class ScannerUiState(
    val barcode: String? = null,
    val boundingRect: BoundingRect? = null,

    val scannerState: ScannerState = ScannerState.Idle,
    //val error: UiError? = null,

    val cameraPermissionState: CameraPermissionState = CameraPermissionState()
)
