package com.yanchelenko.piggybank.modules.features.history.history_impl.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.NavigationDestination
import com.yanchelenko.piggybank.modules.features.history.history_api.HistoryEntry
import com.yanchelenko.piggybank.modules.features.history.history_api.HistoryRouter
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.HistoryMainScreen
import javax.inject.Inject

class HistoryEntryImpl @Inject constructor(
    private val historyRouter: HistoryRouter
) : HistoryEntry {

    override val destination: NavigationDestination = AppDestination.HistoryDestination

    override fun NavGraphBuilder.register() {
        composable(
            route = destination.routeForRegistration,
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            val onNavigateToProductDetails = remember(historyRouter) {
                { productId: Long -> historyRouter.openProductDetails(productId = productId) }
            }

            HistoryMainScreen(onNavigateToProductDetails = onNavigateToProductDetails)
        }
    }
}
