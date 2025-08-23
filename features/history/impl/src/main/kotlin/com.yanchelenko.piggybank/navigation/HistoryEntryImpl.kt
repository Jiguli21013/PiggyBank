package com.yanchelenko.piggybank.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.HistoryEntry
import com.yanchelenko.piggybank.HistoryRouter
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.destinations.NavigationDestination
import com.yanchelenko.piggybank.presentation.HistoryMainScreen
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
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = tween(300)
                )
            }
        ) {
            val onNavigateToProductDetails = remember(historyRouter) {
                { productId: Long -> historyRouter.openProductDetails(productId = productId) }
            }

            HistoryMainScreen(onNavigateToProductDetails = onNavigateToProductDetails)
        }
    }
}
