package com.yanchelenko.piggybank.modules.core.core_api.debugTools

import kotlinx.coroutines.flow.StateFlow

interface Fps {
    val fps: StateFlow<Int>
    fun start()
    fun stop()
}
