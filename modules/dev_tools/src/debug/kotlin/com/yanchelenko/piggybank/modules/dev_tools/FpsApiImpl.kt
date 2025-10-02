package com.yanchelenko.piggybank.modules.dev_tools

import android.view.Choreographer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

internal class FpsApiImpl : FpsApi, Choreographer.FrameCallback {

    private val mainScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val choreographer by lazy(LazyThreadSafetyMode.NONE) { Choreographer.getInstance() }

    private var lastFrameNanos: Long = 0L
    private var frames: Int = 0
    private var accTimeMs: Double = 0.0
    private var emaFps: Double = 0.0

    private val _fps = MutableStateFlow(0)
    override val fps: StateFlow<Int> = _fps

    private var running = false

    override fun doFrame(frameTimeNanos: Long) {
        if (!running) return

        if (lastFrameNanos != 0L) {
            val dtMs = (frameTimeNanos - lastFrameNanos) / NANOS_IN_MILLIS
            accTimeMs += dtMs
            frames++

            if (accTimeMs >= SAMPLE_WINDOW_MS) {
                // instantaneous FPS for the window: frames / (window duration in seconds)
                val instFps = frames * (MILLIS_IN_SECOND / accTimeMs)

                // exponential moving average smoothing
                emaFps = if (emaFps == 0.0) instFps
                else EMA_ALPHA * instFps + (1 - EMA_ALPHA) * emaFps

                _fps.value = emaFps.roundToInt().coerceIn(0, MAX_FPS)

                // reset window counters
                frames = 0
                accTimeMs = 0.0
            }
        }

        lastFrameNanos = frameTimeNanos

        choreographer.postFrameCallback(this)
    }

    override fun start() {
        if (running) return
        running = true

        // reset window state
        lastFrameNanos = 0L
        frames = 0
        accTimeMs = 0.0
        emaFps = 0.0

        mainScope.launch { choreographer.postFrameCallback(this@FpsApiImpl) }
    }

    override fun stop() {
        if (!running) return
        running = false

        mainScope.launch { choreographer.removeFrameCallback(this@FpsApiImpl) }
        mainScope.cancel()
    }

    private companion object {
        private const val NANOS_IN_MILLIS: Double = 1_000_000.0
        private const val MILLIS_IN_SECOND: Double = 1000.0

        // Sampling window size in milliseconds before updating FPS value
        private const val SAMPLE_WINDOW_MS: Double = 500.0

        // Exponential moving average smoothing factor (0..1)
        private const val EMA_ALPHA: Double = 0.2

        // Upper bound for FPS clamping (safety margin)
        private const val MAX_FPS: Int = 240
    }

}
