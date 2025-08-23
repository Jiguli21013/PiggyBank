package com.yanchelenko.piggybank.presentation.state

data class CameraPermissionState(
    val granted: Boolean = false,
    val requested: Boolean = false,
    val showSettings: Boolean = false
)
