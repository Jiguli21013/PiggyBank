package com.yanchelenko.piggybank.navigation.api

interface ProductDetailsNavigator {
    fun navigateToEditProduct(productId: Long)
    fun navigateBack()
}
