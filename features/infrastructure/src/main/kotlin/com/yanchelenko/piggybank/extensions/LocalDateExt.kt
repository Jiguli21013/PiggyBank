package com.yanchelenko.piggybank.extensions

import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val DD_MMM_YYYY = "dd MMMM yyyy"

fun LocalDate.formatAsHeader(
    pattern: String = DD_MMM_YYYY,
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
