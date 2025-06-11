package com.yanchelenko.piggybank.fearues.history.presentation.models

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import java.time.LocalDate

data class HistoryProductGroupUI(
    val date: LocalDate,
    val products: List<ProductUiModel>
)
