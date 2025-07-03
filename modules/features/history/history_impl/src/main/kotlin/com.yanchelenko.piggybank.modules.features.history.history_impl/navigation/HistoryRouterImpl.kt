package com.yanchelenko.piggybank.modules.features.history.history_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.history.history_api.HistoryRouter
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
