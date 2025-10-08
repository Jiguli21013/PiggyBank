package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation

import android.Manifest
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.permissions.PermissionManager
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state.ScannerEffect
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state.ScannerEvent
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state.ScannerState
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state.ScannerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val permissionManager: PermissionManager,
    private val logger: Logger,
) : BaseViewModel<ScannerEvent, ScannerUiState, ScannerEffect>(
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
