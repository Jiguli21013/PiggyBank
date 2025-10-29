package com.yanchelenko.piggybank.modules.features.cart.cart_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.cart.cart_api.CartRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRouterImpl @Inject constructor(
    dispatcher: NavigationDispatcher
) : CartRouter, BaseRouter(navigationDispatcher = dispatcher) {

    override fun openProductOfCartDetails(productId: Long) {
        val route = AppDestination.ProductDetailsDestination(productId = productId).fullRoute()
        navigateTo(destination = route)
    }

    override fun openHistoryOfCarts() {
        val route = AppDestination.HistoryOfCartsDestination.fullRoute()
        navigateTo(destination = route)
    }
}
