package com.yanchelenko.piggybank.core.debugUI.release

import androidx.compose.runtime.Composable
import com.theapache64.rebugger.Rebugger

@Composable
fun WithDebug(
    trackMap: Map<String, Any?>,
    composableName: String? = null,
    content: @Composable () -> Unit
) {
    content()
}
