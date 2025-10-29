package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models

import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import kotlinx.datetime.LocalDate

sealed class ListItem {
    data class DateHeaderUiModel(val date: LocalDate) : ListItem()
    data class ScannedProductItemUiModel(val scannedProduct: ScannedProductUiModel) : ListItem()
}
