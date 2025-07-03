package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models

import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import kotlinx.datetime.LocalDate

sealed class ListItem {
    data class DateHeader(val date: LocalDate) : ListItem()
    data class ProductItem(val product: ProductUiModel) : ListItem()
}
