package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_api

import com.yanchelenko.piggybank.modules.core.core_api.navigation.CommonRouter

interface HistoryOfCartsRouter : CommonRouter {
    fun openCartDetails(cartId: Long)
}
