package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models

import kotlinx.datetime.LocalDate

sealed class ListItem {
    data class DateHeaderUiModel(val date: LocalDate) : ListItem()
    data class HistoryCartItemUiModel(val cart: CartUiModel) : ListItem()
}
