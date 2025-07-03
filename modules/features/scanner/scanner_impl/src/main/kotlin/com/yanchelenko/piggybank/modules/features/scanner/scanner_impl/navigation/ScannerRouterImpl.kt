package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.scanner.scanner_api.ScannerRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScannerRouterImpl @Inject constructor(
    private val dispatcher: NavigationDispatcher
) : ScannerRouter, BaseRouter(dispatcher) {

    override fun navigateToInsertProduct(barcode: String) {
        val route = AppDestination.InsertProductDestination(barcode = barcode).fullRoute()
        navigateTo(destination = route)
    }
}
