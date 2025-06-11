package com.yanchelenko.piggybank.features.scanner.presentation.components

import android.graphics.Rect
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.yanchelenko.piggybank.features.scanner.presentation.state.ScannerEvent
import com.yanchelenko.piggybank.features.scanner.presentation.utils.BarcodeAnalyzer
import kotlinx.coroutines.delay

@Composable
fun AndroidCameraView(
    barcodeAnalyzer: BarcodeAnalyzer,
    modifier: Modifier = Modifier,
    onEvent: (ScannerEvent) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var barcode by remember { mutableStateOf<String?>(null) }
    var boundingRect by remember { mutableStateOf<Rect?>(null) }
    var qrCodeDetected by remember { mutableStateOf(false) }

    val cameraController = remember { LifecycleCameraController(context) }

    val analyzer = remember {
        barcodeAnalyzer.buildAnalyzer(
            onResult = {
                barcode = it.rawValue
                boundingRect = it.boundingBox
                qrCodeDetected = true
            },
            onNotFound = {
                qrCodeDetected = false
            }
        )
    }

    DisposableEffect(Unit) {
        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(context),
            analyzer
        )
        cameraController.bindToLifecycle(lifecycleOwner)
        onDispose {
            cameraController.unbind()
        }
    }

    AndroidView(
        factory = { ctx -> PreviewView(ctx).apply { controller = cameraController } },
        modifier = modifier.fillMaxSize()
    )

    if (qrCodeDetected && barcode != null) {
        LaunchedEffect(barcode) {
            delay(timeMillis = 150)
            onEvent(ScannerEvent.OnBarcodeScanned(barcode = barcode!!))
        }
        DrawRectangle(rect = boundingRect?.toComposeRect())
    } else {
        BlinkingCenteredText(text = "Наведите на штрих-код")
    }
}
