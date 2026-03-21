package com.yanchelenko.piggybank.modules.dev_tools.canary

import leakcanary.AppWatcher

object LeakSandbox {
    private var leakyRef: Any? = null

    fun makeDemoLeak() {
        val victim = Any()
        leakyRef = victim
        AppWatcher.objectWatcher.watch(
            watchedObject = victim,
            description = "Demo leak via LeakSandbox.leakyRef"
        )
    }

    fun clearDemoLeak() {
        leakyRef = null
    }
}
