package com.yanchelenko.piggybank.fearues.history.presentation.models

import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import kotlinx.datetime.LocalDate

sealed class ListItem {
    data class DateHeader(val date: LocalDate) : ListItem()
    data class ProductItem(val product: ProductUiModel) : ListItem()
}
