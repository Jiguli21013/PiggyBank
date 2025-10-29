package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.navigation

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
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_api.HistoryOfCartsEntry
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_api.HistoryOfCartsRouter
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.HistoryOfCartsMainScreen
import javax.inject.Inject

class HistoryOfCartsEntryImpl @Inject constructor(
    private val historyOfCartsRouter: HistoryOfCartsRouter
) : HistoryOfCartsEntry {

    override val destination: NavigationDestination = AppDestination.HistoryOfCartsDestination

    override fun NavGraphBuilder.register() {
        composable(
            route = destination.routeForRegistration,
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            val onNavigateToCartDetails = remember(key1 = historyOfCartsRouter) {
                { cartId: Long -> historyOfCartsRouter.openCartDetails(cartId = cartId) }
            }

            HistoryOfCartsMainScreen(onNavigateToCartDetails = onNavigateToCartDetails)
        }
    }
}
