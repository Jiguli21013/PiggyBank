package com.yanchelenko.piggybank

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.isSystemInDarkTheme
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
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
import com.yanchelenko.piggybank.ui.AppScaffold
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavEvent
import kotlinx.coroutines.launch

@Composable
fun PiggyBankApp(
    navDispatcher: NavigationDispatcher,
    cartItemsCount: Int,
    appTheme: AppTheme,
) {
    val context = LocalContext.current.applicationContext

    val featureEntries = hiltFeatureEntries(context = context)
    val logger = hiltLogger(context = context)

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val isDarkTheme = when (appTheme) {
        AppTheme.SYSTEM -> isSystemInDarkTheme()
        AppTheme.DARK -> true
        AppTheme.LIGHT -> false
    }

    PiggyBankTheme(
        useDarkTheme = isDarkTheme,
    ) {
        val coroutineScope = rememberCoroutineScope()

        AppScaffold(
            currentRoute = currentRoute,
            cartItemsCount = cartItemsCount,
            onBottomNavigationSelected = { route ->
                logger.d("PiggyBankApp", "BottomNav selected: $route")
                coroutineScope.launch { navDispatcher.emit(NavEvent.NavigateRoot(route = route)) }
            },
            onSettingsClickClicked = {
                logger.d("PiggyBankApp", "settings selected")
                navController.navigate(AppDestination.SettingsDestination.fullRoute())
            },
            onNavigateBack = {
                logger.d("PiggyBankApp", "Navigation Back Button")
                navController.navigateUp()
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
