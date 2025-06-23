package com.yanchelenko.piggybank.features.scanner.presentation

import android.Manifest
import com.yanchelenko.permissions.domain.PermissionManager
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerEffect
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerEvent
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerState
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerUiState
import com.yanchelneko.piggybank.common.core_utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val permissionManager: PermissionManager,
    logger: Logger
) : BaseViewModel<ScannerEvent, ScannerUiState, ScannerEffect>( //todo CommonUiState
    logger = logger,
    initialState = ScannerUiState()
) {

    override fun onEvent(event: ScannerEvent) {
        logger.d(LOG_TAG, "Received event: $event")

        when (event) {
            is ScannerEvent.OnBarcodeScanned -> {
                logger.d(LOG_TAG, "Barcode scanned: ${event.barcode}")
                sendEffect { ScannerEffect.NavigateToInsertProduct(barcode = event.barcode) }
            }

            is ScannerEvent.CheckCameraPermission, ScannerEvent.OnCameraPermissionMissing -> {
                logger.d(LOG_TAG, "Checking camera permission...")
                checkCameraPermission()
            }

            is ScannerEvent.OnCameraPermissionResult -> {
                logger.d(LOG_TAG, "Camera permission result: granted=${event.granted}")
                handlePermissionResult(granted = event.granted)
            }

            is ScannerEvent.OnCameraSettingsClicked -> {
                logger.d(LOG_TAG, "Navigating to camera settings")
                sendEffect { ScannerEffect.OpenCameraSettings }
            }
        }
    }

    private fun checkCameraPermission() {
        val granted = permissionManager.hasPermission(Manifest.permission.CAMERA)
        logger.d(LOG_TAG, "Permission granted: $granted")

        if (granted) {
            logger.d(LOG_TAG, "Camera is ready to use")
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
            logger.d(LOG_TAG, "Requesting system camera permission")
            sendEffect { ScannerEffect.RequestSystemCameraPermission }
        }
    }

    private fun handlePermissionResult(granted: Boolean) {
        val shouldShowRationale = permissionManager.shouldShowRationale(Manifest.permission.CAMERA)
        val showSettings = !granted && !shouldShowRationale

        logger.d(
            LOG_TAG,
            "Handle permission result: granted=$granted, shouldShowRationale=$shouldShowRationale, showSettings=$showSettings"
        )

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

    private companion object {
        const val LOG_TAG = "ScannerViewModel"
    }
}
