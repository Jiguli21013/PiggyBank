package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.ProductInsertRouter
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductInsertRouterImpl @Inject constructor(
    dispatcher: NavigationDispatcher
) : ProductInsertRouter, BaseRouter(dispatcher) {
    override fun navigateBack() {
        navigateBack()
    }

    override fun navigateToScanner() {
        val route = AppDestination.ScannerDestination.fullRoute()
        navigateTo(destination = route)
    }
}
