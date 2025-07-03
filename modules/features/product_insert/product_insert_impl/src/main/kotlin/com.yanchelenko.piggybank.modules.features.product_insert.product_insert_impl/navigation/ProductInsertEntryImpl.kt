package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.InsertProductMainScreen
import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.NavigationDestination
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_api.ProductInsertRouter
import javax.inject.Inject

class ProductInsertEntryImpl @Inject constructor(
    private val productInsertRouter: ProductInsertRouter
) : FeatureEntry {

    override val destination: NavigationDestination = AppDestination.InsertProductDestination.Meta

    override fun NavGraphBuilder.register() {
        composable(
            route = destination.routeForRegistration,
            arguments = destination.arguments,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            val onNavigateBack = remember(productInsertRouter) {
                { productInsertRouter.navigateToScanner() }
            }

            InsertProductMainScreen(onNavigateBack = onNavigateBack)
        }
    }
}
