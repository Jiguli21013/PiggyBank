package com.yanchelenko.piggybank.extensions

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return (this * factor).roundToInt() / factor
}

fun Double.formatIfNonZero(): String = takeIf { it != 0.0 }?.toString().orEmpty()
