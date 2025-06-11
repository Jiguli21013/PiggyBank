package com.yanchelenko.piggybank.features.scanner.presentation.models

sealed interface UiError {
    data object CameraUnavailable : UiError
    data object PermissionDenied : UiError
    data class Message(val text: String) : UiError
}
