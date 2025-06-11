package com.yanchelenko.piggybank.features.product_details.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yanchelenko.piggybank.features.product_details.presentation.ProductDetailsScreen
import com.yanchelenko.piggybank.navigation.api.ProductDetailsNavigator
import com.yanchelenko.piggybank.navigation.destinations.AppDestination

fun NavGraphBuilder.productDetailsGraph(productDetailsNavigator: ProductDetailsNavigator) {
    composable(
        route = AppDestination.ProductDetails.routeTemplate,
        arguments = AppDestination.ProductDetails.arguments,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
    ) {
        ProductDetailsScreen(
            onNavigateToEditProduct = { productId ->
                productDetailsNavigator.navigateToEditProduct(productId)
            },
            onNavigateBack = {
                productDetailsNavigator.navigateBack()
            }
        )
    }
}
