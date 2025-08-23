package com.yanchelenko.piggybank.logger

import android.util.Log

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
