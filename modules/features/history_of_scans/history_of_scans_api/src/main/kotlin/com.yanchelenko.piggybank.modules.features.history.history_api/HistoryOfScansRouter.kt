package com.yanchelenko.piggybank.modules.features.history.history_api

import com.yanchelenko.piggybank.modules.core.core_api.navigation.CommonRouter

interface HistoryOfScansRouter : CommonRouter {
    fun openProductDetails(productId: Long)
}
