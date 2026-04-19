package com.yanchelenko.piggybank.modules.dev_tools.rebugger

import com.theapache64.rebugger.Rebugger
import androidx.compose.runtime.Composable

@Composable
fun TrackRecomposition(
    composableName: String,
    trackMap: Map<String, Any?>
) {
    Rebugger(
        composableName = composableName,
        trackMap = trackMap
    )
}
