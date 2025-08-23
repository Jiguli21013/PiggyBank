package com.yanchelenko.piggybank.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.ProductDetailsEntry
import com.yanchelenko.piggybank.ProductDetailsRouter
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.destinations.NavigationDestination
import com.yanchelenko.piggybank.presentation.ProductDetailsScreen
import javax.inject.Inject

class ProductDetailsEntryImpl @Inject constructor(
    private val productDetailsRouter: ProductDetailsRouter,
) : ProductDetailsEntry {

    override val destination: NavigationDestination = AppDestination.ProductDetailsDestination.Meta

    override fun NavGraphBuilder.register() {
        composable(
            route = destination.routeForRegistration,
            arguments = destination.arguments,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                )
            },
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
            // remember for stability to prevent ProductDetailsScreen from recomposing 3 times unnecessarily
            val onNavigateToEditProduct = remember(productDetailsRouter) {
                { productId: Long -> productDetailsRouter.navigateToEditProduct(productId = productId) }
            }
            // remember for stability to prevent ProductDetailsScreen from recomposing 3 times unnecessarily
            val onNavigateBack = remember(productDetailsRouter) {
                { productDetailsRouter.navigateBack() }
            }

            ProductDetailsScreen(
                onNavigateToEditProduct = onNavigateToEditProduct,
                onNavigateBack = onNavigateBack
            )
        }
    }
}
