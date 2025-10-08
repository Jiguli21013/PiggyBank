package com.yanchelenko.piggybank.modules.dev_tools.logger

import android.util.Log
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AndroidLogger @Inject constructor() : Logger {
    override fun d(tag: String, message: String) {
        Log.d(tag.ensureTag(), message)
    }
    override fun e(tag: String, message: String) {
        Log.e(tag.ensureTag(), message)
    }
}

private fun String.ensureTag(): String = if (length <= 23) this else take(23)
