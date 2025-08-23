package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.ScannerRouter
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
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
