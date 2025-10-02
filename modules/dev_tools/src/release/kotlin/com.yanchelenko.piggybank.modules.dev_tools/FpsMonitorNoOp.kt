package com.yanchelenko.piggybank.modules.dev_tools

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class FpsMonitorNoOp : FpsMonitor {
    private val _fps = MutableStateFlow(0)
    override val fps: StateFlow<Int> = _fps
    override fun start() {}
    override fun stop() {}
}
