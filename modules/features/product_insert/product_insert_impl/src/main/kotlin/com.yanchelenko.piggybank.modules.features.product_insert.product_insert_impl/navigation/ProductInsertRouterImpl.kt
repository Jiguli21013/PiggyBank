package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_api.ProductInsertRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductInsertRouterImpl @Inject constructor(
    dispatcher: NavigationDispatcher
) : ProductInsertRouter, BaseRouter(dispatcher) {
    override fun navigateBack() { navigateBack() }
    override fun navigateToScanner() {
        val route = AppDestination.ScannerDestination.fullRoute()
        navigateTo(destination = route)
    }
}
