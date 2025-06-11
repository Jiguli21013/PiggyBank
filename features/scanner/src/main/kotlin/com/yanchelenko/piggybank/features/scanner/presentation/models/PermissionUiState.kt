package com.yanchelenko.piggybank.features.scanner.presentation.models

data class PermissionUiState(
    val isGranted: Boolean = false,
    val shouldShowRationale: Boolean = false,
    val permanentlyDenied: Boolean = false
)
