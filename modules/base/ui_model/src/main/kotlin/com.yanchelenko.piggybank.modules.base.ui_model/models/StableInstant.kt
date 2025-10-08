package com.yanchelenko.piggybank.modules.base.ui_model.models

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class StableInstant(val epochMillis: Long) {
    companion object {
        val DISTANT_PAST = StableInstant(Long.MIN_VALUE)
        val DISTANT_FUTURE = StableInstant(Long.MAX_VALUE)
    }
}

fun Instant.toStable(): StableInstant = StableInstant(toEpochMilliseconds())
fun StableInstant.toInstant(): Instant = Instant.fromEpochMilliseconds(epochMillis)

/** Быстрый ключ дня для сравнения дат (без аллокаций LocalDate). */
fun StableInstant.dayKey(timeZone: TimeZone = TimeZone.currentSystemDefault()): Int =
    toInstant().toLocalDateTime(timeZone = timeZone).date.toEpochDays()

/** Восстановить LocalDate из dayKey при вставке заголовка. */
fun dayKeyToDate(dayKey: Int): LocalDate = LocalDate.fromEpochDays(epochDays = dayKey)
