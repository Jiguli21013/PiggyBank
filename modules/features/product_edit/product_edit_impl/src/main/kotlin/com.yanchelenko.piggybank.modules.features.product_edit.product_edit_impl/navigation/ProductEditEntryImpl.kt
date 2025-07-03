package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.NavigationDestination
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_api.ProductEditEntry
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_api.ProductEditRouter
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.EditProductScreen
import javax.inject.Inject

class ProductEditEntryImpl @Inject constructor(
    private val productEditRouter: ProductEditRouter
) : ProductEditEntry {

    override val destination: NavigationDestination = AppDestination.ProductEditDestination.Meta

    override fun NavGraphBuilder.register() {
        composable(
            route = destination.routeForRegistration,
            arguments = destination.arguments,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            val onNavigateBack = remember(productEditRouter) {
                { productEditRouter.navigateBack() }
            }

            EditProductScreen(onNavigateBack = onNavigateBack)
        }
    }
}
