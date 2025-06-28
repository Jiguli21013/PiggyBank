package com.yanchelenko.piggybank.core.debugUI.debug

import androidx.compose.runtime.Composable
import com.theapache64.rebugger.Rebugger

// а это что?
@Composable
fun WithDebug(
    trackMap: Map<String, Any?>,
    composableName: String? = null,
    content: @Composable () -> Unit
) {
    Rebugger(trackMap = trackMap, composableName = composableName)
    content()
}
