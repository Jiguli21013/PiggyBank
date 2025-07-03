package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.product_details.product_details_api.ProductDetailsRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDetailsRouterImpl @Inject constructor(
    private val dispatcher: NavigationDispatcher
) : ProductDetailsRouter, BaseRouter(dispatcher) {

    override fun navigateToEditProduct(productId: Long) {
        val route = AppDestination.ProductEditDestination(productId = productId).fullRoute()
        navigateTo(destination = route)
    }
}
