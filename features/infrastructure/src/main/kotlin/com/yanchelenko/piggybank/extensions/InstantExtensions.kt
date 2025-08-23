package com.yanchelenko.piggybank.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Расширение для преобразования kotlinx.datetime.Instant в LocalDate
 * с учётом системного часового пояса.
 */

fun Instant.toLocalDateInSystemZone(): LocalDate =
    toLocalDateTime(TimeZone.currentSystemDefault()).date
