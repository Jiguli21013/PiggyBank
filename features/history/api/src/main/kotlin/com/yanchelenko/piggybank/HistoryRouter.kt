package com.yanchelenko.piggybank

import com.yanchelenko.piggybank.navigation.CommonRouter

interface HistoryRouter : CommonRouter {
    fun openProductDetails(productId: Long)
}
