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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yanchelenko.piggybank.di.LoggerEntryPoint
import com.yanchelenko.piggybank.navigation.AppNavHost
import com.yanchelenko.piggybank.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.navigation.registry.ScreenRegistry
import com.yanchelenko.piggybank.ui.scaffold.AppScaffold
import com.yanchelenko.piggynank.core.ui.dimens.ProvideDimens
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelneko.piggybank.common.core_utils.dispatchers.AppDispatchers
import com.yanchelneko.piggybank.common.core_utils.dispatchers.DefaultDispatchersProvider
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch

@Composable
fun PiggyBankApp(
    navDispatcher: NavigationDispatcher,
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val context = LocalContext.current.applicationContext
    val screenMeta = ScreenRegistry.resolveScreenMeta(currentRoute = currentRoute)
    val dispatchers = remember { AppDispatchers(DefaultDispatchersProvider()) }

    val logger = remember {
        EntryPointAccessors
            .fromApplication(context, LoggerEntryPoint::class.java)
            .logger()
    }

    val coroutineScope = rememberCoroutineScope()

    ProvideDimens {
        PiggyBankTheme {
            AppScaffold(
                screenMeta = screenMeta,
                currentRoute = currentRoute,
                onNavigationSelected = { route ->
                    logger.d("Navigation", "BottomNav selected: $route")
                    coroutineScope.launch {
                        navDispatcher.emit(event = NavEvent.NavigateRoot(route = route))
                    }
                }
            ) { padding ->
                AppNavHost(
                    navController = navController,
                    navDispatcher = navDispatcher,
                    dispatchers =  dispatchers,
                    logger = logger,
                    modifier = Modifier
                        .padding(paddingValues = padding)
                        .consumeWindowInsets(paddingValues = padding)
                )
            }

        }
    }
}
