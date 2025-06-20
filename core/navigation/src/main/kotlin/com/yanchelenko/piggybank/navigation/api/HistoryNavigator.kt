package com.yanchelenko.piggybank.navigation.api

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel

interface HistoryNavigator {
    fun navigateToProductDetails(product: ProductUiModel)
}
