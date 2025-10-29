package com.yanchelenko.piggybank.modules.features.cart.cart_impl.navigation

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
import com.yanchelenko.piggybank.modules.features.cart.cart_api.CartEntry
import com.yanchelenko.piggybank.modules.features.cart.cart_api.CartRouter
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.CartMainScreen
import javax.inject.Inject

class CartEntryImpl @Inject constructor(
    private val cartRouter: CartRouter
) : CartEntry {

    override val destination: NavigationDestination = AppDestination.CartDestination

    override fun NavGraphBuilder.register() {
        composable(
            route = destination.routeForRegistration,
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            val onNavigateToProductOfCartDetails = remember(key1 = cartRouter) {
                { productId: Long -> cartRouter.openProductOfCartDetails(productId = productId) }
            }

            CartMainScreen(
                onNavigateToHistoryOfCarts = { cartRouter.openHistoryOfCarts() },
                onNavigateToProductOfCartDetails = onNavigateToProductOfCartDetails
            )
        }
    }
}
