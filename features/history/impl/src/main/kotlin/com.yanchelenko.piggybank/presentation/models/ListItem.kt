package com.yanchelenko.piggybank.presentation.models

import com.yanchelenko.piggybank.models.Product
import kotlinx.datetime.LocalDate

sealed class ListItem {
    data class DateHeaderUiModel(val date: LocalDate) : ListItem()
    data class ProductItemUiModel(val product: Product) : ListItem()
}
