package com.yanchelenko.piggynank.core.ui.dimens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
//todo  для multi-screen / foldable / large-screen устройств.
@Composable
fun ProvideDimens(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context)
    val widthDp = (metrics.bounds.width() / context.resources.displayMetrics.density).dp

    val dimens = when {
        widthDp < 600.dp -> compactDimens
        widthDp < 840.dp -> mediumDimens
        else -> expandedDimens
    }

    CompositionLocalProvider(LocalDimens provides dimens) {
        content()
    }
}
