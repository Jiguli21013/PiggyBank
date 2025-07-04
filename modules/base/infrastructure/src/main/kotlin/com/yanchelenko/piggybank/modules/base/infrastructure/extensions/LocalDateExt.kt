package com.yanchelenko.piggybank.modules.base.infrastructure.extensions

import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun LocalDate.formatAsHeader(
    pattern: String = "dd MMMM yyyy", //todo
    locale: Locale = Locale.getDefault()
): String {
    val formatter = SimpleDateFormat(pattern, locale)
    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, monthNumber - 1)
        set(Calendar.DAY_OF_MONTH, dayOfMonth)
    }
    return formatter.format(calendar.time)
}
