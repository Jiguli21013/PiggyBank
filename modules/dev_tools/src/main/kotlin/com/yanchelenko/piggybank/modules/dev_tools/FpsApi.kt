package com.yanchelenko.piggybank.modules.dev_tools

import kotlinx.coroutines.flow.StateFlow

interface FpsApi {
    val fps: StateFlow<Int>
    fun start()
    fun stop()
}
