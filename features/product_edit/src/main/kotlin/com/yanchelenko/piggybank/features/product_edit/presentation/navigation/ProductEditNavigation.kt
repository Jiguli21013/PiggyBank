package com.yanchelenko.piggybank.features.product_edit.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yanchelenko.piggybank.features.product_edit.presentation.EditProductScreen
import com.yanchelenko.piggybank.navigation.api.EditProductNavigator
import com.yanchelenko.piggybank.navigation.destinations.AppDestination

fun NavGraphBuilder.productEditGraph(editProductNavigator: EditProductNavigator) {
    composable(
        route = AppDestination.EditProduct.routeTemplate,
        arguments = AppDestination.EditProduct.arguments,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
    ) {
        EditProductScreen(onNavigateBack = { editProductNavigator.navigateBack() })
    }
}
