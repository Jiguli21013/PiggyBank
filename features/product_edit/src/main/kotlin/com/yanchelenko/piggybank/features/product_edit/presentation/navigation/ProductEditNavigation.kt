package com.yanchelenko.piggybank.features.product_edit.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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
    ) { entry ->
        entry.savedStateHandle // тут тоже можно использовать savedStateHandle
        val onNavigateBack = remember(editProductNavigator) {
            { editProductNavigator.navigateBack() }
        }

        EditProductScreen(onNavigateBack = onNavigateBack)
    }
}
