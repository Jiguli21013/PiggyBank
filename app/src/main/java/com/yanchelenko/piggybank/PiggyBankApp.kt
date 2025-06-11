package com.yanchelenko.piggybank

import androidx.compose.foundation.layout.consumeWindowInsets
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yanchelenko.piggybank.navigation.AppNavHost
import com.yanchelenko.piggybank.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.navigation.registry.ScreenRegistry
import com.yanchelenko.piggybank.ui.scaffold.AppScaffold
import com.yanchelenko.piggynank.core.ui.dimens.ProvideDimens
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelneko.piggybank.common.core_utils.dispatchers.AppDispatchers
import com.yanchelneko.piggybank.common.core_utils.dispatchers.DefaultDispatchersProvider
import kotlinx.coroutines.launch

@Composable
fun PiggyBankApp(
    navDispatcher: NavigationDispatcher
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val screenMeta = ScreenRegistry.resolveScreenMeta(currentRoute = currentRoute)

    val dispatchers = remember { AppDispatchers(DefaultDispatchersProvider()) }

    LaunchedEffect(navDispatcher) {
        navDispatcher.navEvents.collect { event ->
            when (event) {
                is NavEvent.Navigate -> navController.navigate(route = event.route)

                is NavEvent.NavigateBack -> navController.popBackStack()

                is NavEvent.NavigateRoot -> {
                    navController.popBackStack(
                        destinationId = navController.graph.startDestinationId,
                        inclusive = false
                    )
                    navController.navigate(route = event.route)
                }

                is NavEvent.NavigateAndPopUp -> {
                    navController.popBackStack()
                    navController.navigate(route = event.route)
                }
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    ProvideDimens {
        PiggyBankTheme {
            AppScaffold(
                screenMeta = screenMeta,
                currentRoute = currentRoute,
                onNavigationSelected = { route ->
                    coroutineScope.launch {
                        navDispatcher.emit(event = NavEvent.NavigateRoot(route = route))
                    }
                }
            ) { padding ->
                AppNavHost(
                    navController = navController,
                    navDispatcher = navDispatcher,
                    dispatchers =  dispatchers,
                    modifier = Modifier
                        .padding(paddingValues = padding)
                        .consumeWindowInsets(paddingValues = padding)
                )
            }

        }
    }
}
