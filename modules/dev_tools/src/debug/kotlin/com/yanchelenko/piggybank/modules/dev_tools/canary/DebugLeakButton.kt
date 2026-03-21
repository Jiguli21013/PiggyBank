package com.yanchelenko.piggybank.modules.dev_tools.canary

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text

@Composable
fun DebugLeakButton(modifier: Modifier = Modifier) {
    Button(onClick = { LeakSandbox.makeDemoLeak() }, modifier = modifier) {
        Text("Make Demo Leak")
    }
}
