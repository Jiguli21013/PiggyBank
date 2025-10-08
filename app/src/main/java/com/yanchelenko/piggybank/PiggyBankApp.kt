package com.yanchelenko.piggybank

import androidx.compose.foundation.layout.consumeWindowInsets
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yanchelenko.piggybank.di.hiltFeatureEntries
import com.yanchelenko.piggybank.di.hiltLogger
import com.yanchelenko.piggybank.navigation.AppNavHost
import com.yanchelenko.piggybank.ui.AppScaffold
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.base.infrastructure.dispatchers.AppDispatchers
import com.yanchelenko.piggybank.modules.base.infrastructure.dispatchers.DefaultDispatchersProvider
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavEvent
import kotlinx.coroutines.launch

@Composable
fun PiggyBankApp(
    navDispatcher: NavigationDispatcher
) {
    val context = LocalContext.current.applicationContext

    val featureEntries = hiltFeatureEntries(context = context)
    val logger = hiltLogger(context = context)

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    //todo подумать че с dispatchers делать
    val dispatchers = remember { AppDispatchers(DefaultDispatchersProvider()) }

    PiggyBankTheme {
        val coroutineScope = rememberCoroutineScope()

        AppScaffold(
            currentRoute = currentRoute,
            onNavigationSelected = { route ->
                logger.d("Navigation", "BottomNav selected: $route")
                coroutineScope.launch { navDispatcher.emit(NavEvent.NavigateRoot(route = route)) }
            }
        ) { padding ->
            AppNavHost(
                logger = logger,
                navController = navController,
                navDispatcher = navDispatcher,
                featureEntries = featureEntries,
                modifier = Modifier
                    .padding(paddingValues = padding)
                    .consumeWindowInsets(paddingValues = padding)
            )
        }
    }
}
