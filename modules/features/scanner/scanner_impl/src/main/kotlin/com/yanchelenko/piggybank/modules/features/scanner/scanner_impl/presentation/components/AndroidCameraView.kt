package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.R
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.data.vision.BarcodeAnalyzer
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.state.ScannerEvent
import kotlinx.coroutines.delay

@Composable
fun AndroidCameraView(
    barcodeAnalyzer: BarcodeAnalyzer,
    modifier: Modifier = Modifier,
    onEvent: (ScannerEvent) -> Unit // todo сделать входные параметры строго typed ?
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var barcode by remember { mutableStateOf<String?>(value = null) }
    var boundingRect by remember { mutableStateOf<Rect?>(value = null) }
    var qrCodeDetected by remember { mutableStateOf(value = false) }

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
        BlinkingCenteredText(text = stringResource(R.string.hint_point_to_barcode))
    }
}
