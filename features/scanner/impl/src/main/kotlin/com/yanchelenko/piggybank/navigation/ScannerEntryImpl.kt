package com.yanchelenko.piggybank.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.ScannerRouter
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.destinations.NavigationDestination
import com.yanchelenko.piggybank.presentation.ScannerScreen
import javax.inject.Inject

class ScannerEntryImpl @Inject constructor(
    private val scannerRouter: ScannerRouter
) : FeatureEntry {

    override val destination: NavigationDestination = AppDestination.ScannerDestination

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
            val onNavigateToInsertProduct = remember(scannerRouter) {
                { barcode: String -> scannerRouter.navigateToInsertProduct(barcode = barcode) }
            }

            ScannerScreen(
                onNavigateToInsertProduct = onNavigateToInsertProduct
            )
        }
    }
}
