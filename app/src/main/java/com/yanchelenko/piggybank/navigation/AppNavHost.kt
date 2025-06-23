package com.yanchelenko.piggybank.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.fearues.history.presentation.navigation.historyGraph
import com.yanchelenko.piggybank.features.product_edit.presentation.navigation.productEditGraph
import com.yanchelenko.piggybank.features.product_insert.presentation.navigation.productInsertGraph
import com.yanchelenko.piggybank.features.product_details.presentation.navigation.productDetailsGraph
import com.yanchelenko.piggybank.features.scanner.presentation.navigation.scannerGraph
import com.yanchelenko.piggybank.navigation.NavigationConstants.NAVIGATION_DEBOUNCE_MS
import com.yanchelenko.piggybank.navigation.api.EditProductNavigator
import com.yanchelenko.piggybank.navigation.api.HistoryNavigator
import com.yanchelenko.piggybank.navigation.api.InsertProductNavigator
import com.yanchelenko.piggybank.navigation.api.ProductDetailsNavigator
import com.yanchelenko.piggybank.navigation.api.ScannerNavigator
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import com.yanchelneko.piggybank.common.core_utils.Logger
import com.yanchelneko.piggybank.common.core_utils.dispatchers.AppDispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavHost(
    navController: NavHostController,
    navDispatcher: NavigationDispatcher,
    dispatchers: AppDispatchers,
    logger: Logger,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        navDispatcher.navEvents
            .debounce(timeoutMillis = NAVIGATION_DEBOUNCE_MS) // защита от двойного клика
            .distinctUntilChanged()
            .collect { event ->
                logger.d("AppNavHost", "Received NavEvent: ${event.javaClass.simpleName} -> $event")
                when (event) {
                    is NavEvent.Navigate -> {
                        navController.navigate(event.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                        }
                    }

                    is NavEvent.NavigateAndPopUp -> {
                        navController.popBackStack(event.popUp, inclusive = true)
                        navController.navigate(event.route) {
                            launchSingleTop = true
                        }
                    }

                    is NavEvent.NavigateRoot -> {
                        navController.navigate(event.route) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }

                    NavEvent.NavigateBack -> {
                        navController.popBackStack()
                    }
                }
            }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestination.Scanner.route,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        modifier = modifier
    ) {

        scannerGraph(scannerNavigator = object : ScannerNavigator {
            override fun navigateToInsertProduct(barcode: String) {
                coroutineScope.launch(dispatchers.provider.main) {
                    val destination = AppDestination.InsertProduct(productId = barcode.toLong())
                    navDispatcher.emit(NavEvent.Navigate(route = destination.fullRoute()))
                }
            }

            override fun navigateBack() {
                coroutineScope.launch(dispatchers.provider.main) {
                    navDispatcher.emit(NavEvent.NavigateBack)
                }
            }
        })

        historyGraph(historyNavigator = object : HistoryNavigator {
            override fun navigateToProductDetails(product: ProductUiModel) {
                val json = Json.encodeToString(value = product)
                val encoded = URLEncoder.encode(json, StandardCharsets.UTF_8.name())

                coroutineScope.launch(dispatchers.provider.main) {
                    val destination = AppDestination.ProductDetails(product = encoded)
                    navDispatcher.emit(NavEvent.Navigate(route = destination.fullRoute()))
                }
            }
        })

        productDetailsGraph(productDetailsNavigator = object : ProductDetailsNavigator {
            override fun navigateToEditProduct(productId: Long) {
                coroutineScope.launch(dispatchers.provider.main) {
                    val destination = AppDestination.EditProduct(productId = productId)
                    navDispatcher.emit(NavEvent.Navigate(route = destination.fullRoute()))
                }
            }

            override fun navigateBack() {
                coroutineScope.launch(dispatchers.provider.main) {
                    navDispatcher.emit(NavEvent.NavigateBack)
                }
            }
        })

        productEditGraph(editProductNavigator = object : EditProductNavigator {
            override fun navigateBack() {
                coroutineScope.launch(dispatchers.provider.main) {
                    navDispatcher.emit(NavEvent.NavigateBack)
                }
            }
        })

        productInsertGraph(insertProductNavigator = object : InsertProductNavigator {
            override fun navigateBack() {
                coroutineScope.launch(dispatchers.provider.main) {
                    navDispatcher.emit(NavEvent.NavigateBack)
                }
            }

            override fun navigateToScanner() {
                coroutineScope.launch(dispatchers.provider.main) {
                    val destination = AppDestination.Scanner
                    navDispatcher.emit(NavEvent.NavigateRoot(route = destination.fullRoute()))
                }
            }
        })
    }
}
