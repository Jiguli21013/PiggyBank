package com.yanchelenko.piggybank.modules.dev_tools

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource

@Composable
fun FpsOverlay(modifier: Modifier) {
    val monitor = remember { FpsApiImpl() }
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> monitor.start()
                Lifecycle.Event.ON_STOP  -> monitor.stop()
                else -> Unit
            }
        }
        lifecycle.addObserver(observer)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            monitor.start()
        }
        onDispose {
            lifecycle.removeObserver(observer)
            monitor.stop()
        }
    }

    var value by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { monitor.fps.collectLatest { value = it } }

    Text(
        text = stringResource(R.string.fps_label, value),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}
