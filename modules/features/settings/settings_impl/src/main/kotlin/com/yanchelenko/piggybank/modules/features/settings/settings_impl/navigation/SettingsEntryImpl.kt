package com.yanchelenko.piggybank.modules.features.settings.settings_impl.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanchelenko.piggybank.modules.core.core_api.navigation.FeatureEntry
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.NavigationDestination
import com.yanchelenko.piggybank.modules.features.settings.settings_api.SettingsRouter
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.SettingsScreen

import javax.inject.Inject

class SettingsEntryImpl @Inject constructor() : FeatureEntry {

    override val destination: NavigationDestination = AppDestination.SettingsDestination

    override fun NavGraphBuilder.register() {
        composable(
            route = destination.routeForRegistration,
            enterTransition = {
                scaleIn(
                    initialScale = 0.7f,
                    animationSpec = tween(300),
                    transformOrigin = TransformOrigin(1f, 0f)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                scaleOut(
                    targetScale = 0.7f,
                    animationSpec = tween(300),
                    transformOrigin = TransformOrigin(1f, 0f)
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                scaleIn(
                    initialScale = 0.7f,
                    animationSpec = tween(300),
                    transformOrigin = TransformOrigin(1f, 0f)
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                scaleOut(
                    targetScale = 0.7f,
                    animationSpec = tween(300),
                    transformOrigin = TransformOrigin(1f, 0f)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            SettingsScreen()
        }
    }
}
