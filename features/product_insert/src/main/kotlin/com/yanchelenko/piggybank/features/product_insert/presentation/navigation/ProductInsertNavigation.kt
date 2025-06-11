package com.yanchelenko.piggybank.features.product_insert.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.features.product_insert.presentation.InsertProductMainScreen
import com.yanchelenko.piggybank.navigation.api.InsertProductNavigator
import com.yanchelenko.piggybank.navigation.destinations.AppDestination

fun NavGraphBuilder.productInsertGraph(insertProductNavigator: InsertProductNavigator) {
    composable(
        route = AppDestination.InsertProduct.routeTemplate,
        arguments = AppDestination.InsertProduct.arguments,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
    ) {
        InsertProductMainScreen(
            onNavigateBack = {
                insertProductNavigator.navigateToScanner()
            }
        )
    }
}
