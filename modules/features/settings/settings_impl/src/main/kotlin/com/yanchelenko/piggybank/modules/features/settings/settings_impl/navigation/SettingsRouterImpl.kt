package com.yanchelenko.piggybank.modules.features.settings.settings_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.settings.settings_api.SettingsRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRouterImpl @Inject constructor(
    private val dispatcher: NavigationDispatcher
) : SettingsRouter, BaseRouter(navigationDispatcher = dispatcher) {

    override fun navigateToSettings() {
        val route = AppDestination.SettingsDestination.fullRoute()
        navigateTo(destination = route)
    }

    override fun navigateBack() { navigateBack() }
}
