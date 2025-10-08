package com.yanchelenko.piggybank.modules.dev_tools.fps

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FpsOverlay(modifier: Modifier = Modifier) {
    val monitor = remember { FpsImpl() }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, monitor) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> monitor.start()
                Lifecycle.Event.ON_STOP  -> monitor.stop()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            monitor.start()
        }
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            monitor.stop()
        }
    }

    var value by remember { mutableIntStateOf(0) }
    LaunchedEffect(monitor) { monitor.fps.collectLatest { value = it } }

    Text(
        text = "$value fps",
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}
