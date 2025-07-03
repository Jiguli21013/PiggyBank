package com.yanchelenko.piggybank.modules.core.core_impl.logger

import android.util.Log
import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger

public fun androidLogcatLogger(): Logger =
    object : Logger {
        override fun d(
            tag: String,
            message: String
        ) {
            Log.d(tag, message)
        }

        override fun e(
            tag: String,
            message: String
        ) {
            Log.e(tag, message)
        }
    }
