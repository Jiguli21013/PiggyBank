package com.yanchelenko.piggybank.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yanchelenko.piggybank.logger.Logger
import com.yanchelenko.piggybank.navigation.NavigationConstants.NAVIGATION_DEBOUNCE_MS
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import kotlinx.coroutines.flow.debounce

@Composable
fun AppNavHost(
    navController: NavHostController,
    featureEntries: Set<@JvmSuppressWildcards FeatureEntry>,
    navDispatcher: NavigationDispatcher,
    logger: Logger,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        navDispatcher.navEvents
            .debounce(NAVIGATION_DEBOUNCE_MS)
            .collect { event ->

                logger.d("AppNavHost", "NavEvent: $event")

                when (event) {
                    is NavEvent.Navigate -> {
                        navController.navigate(event.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                        }
                    }

                    is NavEvent.NavigateRoot -> {
                        navController.navigate(event.route) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }

                    is NavEvent.NavigateBack -> {
                        navController.popBackStack()
                    }
                }
            }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestination.ScannerDestination.routeForRegistration,
        modifier = modifier,
    ) {
        featureEntries.forEach { entry ->
            with(entry) { register() }
        }
    }
}
