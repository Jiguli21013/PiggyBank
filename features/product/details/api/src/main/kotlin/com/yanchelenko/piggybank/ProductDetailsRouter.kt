package com.yanchelenko.piggybank

import com.yanchelenko.piggybank.navigation.CommonRouter

interface ProductDetailsRouter : CommonRouter {
    fun navigateToEditProduct(productId: Long)
}
