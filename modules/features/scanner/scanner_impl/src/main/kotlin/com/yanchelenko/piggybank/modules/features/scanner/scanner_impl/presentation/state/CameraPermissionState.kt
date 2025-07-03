package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state

data class CameraPermissionState(
    val granted: Boolean = false,
    val requested: Boolean = false,
    val showSettings: Boolean = false
)
