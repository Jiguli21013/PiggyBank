package com.yanchelenko.piggybank.fearues.history.presentation.navigation

import com.yanchelenko.piggybank.fearues.history.presentation.HistoryMainScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.navigation.api.HistoryNavigator
import com.yanchelenko.piggybank.navigation.destinations.AppDestination

fun NavGraphBuilder.historyGraph(historyNavigator: HistoryNavigator) {
    composable(
        route = AppDestination.History.route,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
    ) {
        HistoryMainScreen(
            onNavigateToProductDetails = { product ->
                historyNavigator.navigateToProductDetails(product = product)
            }
        )
    }
}
