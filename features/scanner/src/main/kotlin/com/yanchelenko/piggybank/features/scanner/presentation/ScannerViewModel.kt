package com.yanchelenko.piggybank.features.scanner.presentation

import android.Manifest
import com.yanchelenko.permissions.domain.PermissionManager
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerEffect
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerEvent
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerState
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val permissionManager: PermissionManager
) : BaseViewModel<ScannerEvent, ScannerUiState, ScannerEffect>(
    initialState = ScannerUiState()
) {

    override fun onEvent(event: ScannerEvent) {
        when (event) {
            is ScannerEvent.OnBarcodeScanned -> {
                //setState { copy(scannerState = ScannerState.ReadyToScan) }
                sendEffect { ScannerEffect.NavigateToInsertProduct(barcode = event.barcode) }
            }

            is ScannerEvent.CheckCameraPermission, ScannerEvent.OnCameraPermissionMissing -> {
                checkCameraPermission()
            }

            is ScannerEvent.OnCameraPermissionResult -> {
                handlePermissionResult(granted = event.granted)
            }

            is ScannerEvent.OnCameraSettingsClicked -> {
                sendEffect { ScannerEffect.OpenCameraSettings }
            }
        }
    }

    private fun checkCameraPermission() {
        val granted = permissionManager.hasPermission(permission = Manifest.permission.CAMERA)

        if (granted) {
            setState {
                copy(
                    cameraPermissionState = cameraPermissionState.copy(
                        granted = true,
                        requested = true,
                        showSettings = false
                    ),
                    scannerState = ScannerState.ReadyToScan
                )
            }
        } else {
            sendEffect { ScannerEffect.RequestSystemCameraPermission }
        }
    }

    private fun handlePermissionResult(granted: Boolean) {
        val shouldShowRationale = permissionManager.shouldShowRationale(permission = Manifest.permission.CAMERA)
        val showSettings = !granted && !shouldShowRationale

        setState {
            copy(
                cameraPermissionState = cameraPermissionState.copy(
                    granted = granted,
                    requested = true,
                    showSettings = showSettings
                ),
                scannerState = if (granted) ScannerState.ReadyToScan else ScannerState.PermissionDenied
            )
        }
    }
}