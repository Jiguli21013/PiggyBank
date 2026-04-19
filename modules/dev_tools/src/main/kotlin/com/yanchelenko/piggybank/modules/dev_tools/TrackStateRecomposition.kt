package com.yanchelenko.piggybank.modules.dev_tools

import androidx.compose.runtime.Composable
import com.yanchelenko.piggybank.modules.dev_tools.rebugger.TrackRecomposition

@Composable
fun TrackStateRecomposition(
    composableName: String,
    trackMap: Map<String, Any?>
) {
    TrackRecomposition(
        composableName = composableName,
        trackMap = trackMap
    )
}
