package com.yanchelenko.piggybank.modules.features.product_details.product_details_api

import com.yanchelenko.piggybank.modules.core.core_api.navigation.CommonRouter

interface ProductDetailsRouter : CommonRouter {
    fun navigateToEditProduct(productId: Long)
}
