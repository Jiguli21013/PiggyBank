package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_api.HistoryOfCartsRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryOfCartsRouterImpl @Inject constructor(
    private val dispatcher: NavigationDispatcher
) : HistoryOfCartsRouter, BaseRouter(dispatcher) {

    override fun openCartDetails(cardId: Long) {
        val route = AppDestination.CartDestination.fullRoute() //todo id of cart
        navigateTo(destination = route)
    }
}
