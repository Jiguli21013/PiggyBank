package com.yanchelenko.piggybank.presentation

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanchelenko.piggybank.components.CenteredLoader
import com.yanchelenko.piggybank.di.barcode.hiltBarcodeAnalyzer
import com.yanchelenko.piggybank.lifecycle.OnResumeEffect
import com.yanchelenko.piggybank.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.presentation.components.AndroidCameraView
import com.yanchelenko.piggybank.presentation.components.CameraPermissionDeniedContent
import com.yanchelenko.piggybank.presentation.state.ScannerEffect
import com.yanchelenko.piggybank.presentation.state.ScannerEvent
import com.yanchelenko.piggybank.presentation.state.ScannerState
import com.yanchelenko.piggybank.vision.BarcodeAnalyzer

//todo preview
@Composable
fun ScannerScreen(
    modifier: Modifier = Modifier,
    viewModel: ScannerViewModel = hiltViewModel(),
    barcodeAnalyzer: BarcodeAnalyzer = hiltBarcodeAnalyzer(),
    onNavigateToInsertProduct: (String) -> Unit
) {
    val context = LocalContext.current

    val state by viewModel.uiState.collectAsState()
    val effectFlow = viewModel.effect

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        viewModel.onEvent(event = ScannerEvent.OnCameraPermissionResult(granted))
    }

    OnResumeEffect { viewModel.onEvent(event = ScannerEvent.CheckCameraPermission) }

    DisposableEffect(Unit) { onDispose { viewModel.resetLastBarcode() } }

    ScreenWithEffect(
        state = state,
        effectFlow = effectFlow,
        onEvent = viewModel::onEvent,
        modifier = modifier,
        onEffect = { effect ->
            when (effect) {
                is ScannerEffect.NavigateToInsertProduct -> {
                    onNavigateToInsertProduct(effect.barcode)
                }

                is ScannerEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is ScannerEffect.RequestSystemCameraPermission -> {
                    launcher.launch(Manifest.permission.CAMERA)
                }

                is ScannerEffect.OpenCameraSettings -> {
                    context.startActivity(
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                    )
                }
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            when (uiState.scannerState) {
                ScannerState.ReadyToScan -> AndroidCameraView(
                    modifier = innerModifier.fillMaxSize(),
                    onEvent = sendEvent,
                    barcodeAnalyzer = barcodeAnalyzer
                )

                ScannerState.PermissionDenied -> CameraPermissionDeniedContent {
                    sendEvent(ScannerEvent.OnCameraSettingsClicked)
                }

                ScannerState.Idle -> CenteredLoader()
                ScannerState.Error -> {} // todo
                ScannerState.Found -> {} // todo
            }
        }
    )
}
