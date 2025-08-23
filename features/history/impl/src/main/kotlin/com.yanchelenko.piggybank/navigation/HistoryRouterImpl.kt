package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.HistoryRouter
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRouterImpl @Inject constructor(
    private val dispatcher: NavigationDispatcher
) : HistoryRouter, BaseRouter(dispatcher) {

    override fun openProductDetails(productId: Long) {
        val route = AppDestination.ProductDetailsDestination(productId = productId).fullRoute()
        navigateTo(destination = route)
    }
}
