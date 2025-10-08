package com.yanchelenko.piggybank.modules.dev_tools

import android.content.pm.ApplicationInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.theapache64.rebugger.Rebugger

@Composable
fun RebuggerIfDebug(
    composableName: String,
    trackMap: Map<String, Any?> = emptyMap()
) {
    val appCtx = LocalContext.current.applicationContext
    val isDebuggable = remember(appCtx) {
        (appCtx.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
    }

    if (isDebuggable) {
        Rebugger(
            trackMap = trackMap,
            composableName = composableName
        )
    }
}
