package com.yanchelenko.piggybank.modules.base.infrastructure.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Расширение для преобразования kotlinx.datetime.Instant в LocalDate
 * с учётом системного часового пояса.
 */

fun Instant.toLocalDateInSystemZone(): LocalDate {
    return this
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date
}
