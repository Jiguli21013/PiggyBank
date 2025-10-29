package com.yanchelenko.piggybank.modules.features.cart.cart_api

import com.yanchelenko.piggybank.modules.core.core_api.navigation.CommonRouter

interface CartRouter : CommonRouter {
    fun openProductOfCartDetails(productId: Long)
    fun openHistoryOfCarts()
}
