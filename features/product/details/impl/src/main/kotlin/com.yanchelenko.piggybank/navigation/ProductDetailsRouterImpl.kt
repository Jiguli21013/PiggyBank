package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.ProductDetailsRouter
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
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
