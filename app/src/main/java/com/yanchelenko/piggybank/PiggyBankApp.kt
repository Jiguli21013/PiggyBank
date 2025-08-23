package com.yanchelenko.piggybank

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yanchelenko.piggybank.di.hiltFeatureEntries
import com.yanchelenko.piggybank.di.hiltLogger
import com.yanchelenko.piggybank.navigation.AppNavHost
import com.yanchelenko.piggybank.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.ui.AppScaffold
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
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

    PiggyBankTheme {
        val coroutineScope = rememberCoroutineScope()

        AppScaffold(
            currentRoute = currentRoute,
            onNavigationSelected = { route ->
                // может быть достаточно логировать только в AppNavHost ?
                logger.d("Navigation", "BottomNav selected: $route")
                // тут нужен main поток, так что все ок
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
