package com.yanchelenko.piggynank.core.ui

import android.util.Log
import com.yanchelneko.piggybank.common.core_utils.Logger

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
