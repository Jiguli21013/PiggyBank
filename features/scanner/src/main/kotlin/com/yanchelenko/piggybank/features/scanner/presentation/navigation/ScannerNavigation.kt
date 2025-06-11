package com.yanchelenko.piggybank.features.scanner.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.features.scanner.presentation.ScannerScreen
import com.yanchelenko.piggybank.navigation.api.ScannerNavigator
import com.yanchelenko.piggybank.navigation.destinations.AppDestination

fun NavGraphBuilder.scannerGraph(scannerNavigator: ScannerNavigator) {
    composable(
        route = AppDestination.Scanner.route,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
    ) {
        ScannerScreen(
            onNavigateToInsertProduct = { barcode ->
                scannerNavigator.navigateToInsertProduct(barcode = barcode)
            }
        )
    }
}
