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
) : BaseViewModel<ScannerEvent, ScannerUiState, ScannerEffect>(
    logger = logger,
    initialState = ScannerUiState()
) {

    private var lastScannedBarcode: String? = null

    override fun onEvent(event: ScannerEvent) {
        logger.d(LOG_TAG, "Received event: $event")

        when (event) {
            is ScannerEvent.OnBarcodeScanned -> handleBarcode(event.barcode)
            is ScannerEvent.CheckCameraPermission, ScannerEvent.OnCameraPermissionMissing -> checkCameraPermission()
            is ScannerEvent.OnCameraPermissionResult -> handlePermissionResult(event.granted)
            is ScannerEvent.OnCameraSettingsClicked -> {
                logger.d(LOG_TAG, "Navigating to camera settings")
                sendEffect { ScannerEffect.OpenCameraSettings }
            }
        }
    }

    private fun handleBarcode(barcode: String) {
        if (barcode == lastScannedBarcode) {
            logger.d(LOG_TAG, "Duplicate barcode scan ignored: $barcode")
            return
        }

        lastScannedBarcode = barcode
        logger.d(LOG_TAG, "Barcode scanned: $barcode")
        sendEffect { ScannerEffect.NavigateToInsertProduct(barcode) }
    }

    private fun checkCameraPermission() {
        val granted = permissionManager.hasPermission(Manifest.permission.CAMERA)
        logger.d(LOG_TAG, "Permission granted: $granted")

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
        val shouldShowRationale = permissionManager.shouldShowRationale(Manifest.permission.CAMERA)
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

    fun resetLastBarcode() {
        lastScannedBarcode = null
    }

    private companion object {
        const val LOG_TAG = "ScannerViewModel"
    }
}
