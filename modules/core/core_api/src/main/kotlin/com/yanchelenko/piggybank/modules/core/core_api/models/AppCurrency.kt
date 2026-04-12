package com.yanchelenko.piggybank.modules.core.core_api.models

enum class AppCurrency(
    val code: String,
    val symbol: String
) {
    EUR(code = "EUR", symbol = "€"),
    USD(code = "USD", symbol = "$"),
    UAH(code = "UAH", symbol = "₴");

    companion object {
        fun fromCode(code: String?): AppCurrency {
            return entries.firstOrNull { it.code == code } ?: EUR
        }
    }
}
