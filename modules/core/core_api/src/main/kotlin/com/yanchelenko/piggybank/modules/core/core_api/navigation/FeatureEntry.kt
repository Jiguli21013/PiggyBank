package com.yanchelenko.piggybank.modules.core.core_api.navigation

import androidx.navigation.NavGraphBuilder
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.NavigationDestination

interface FeatureEntry {
    val destination: NavigationDestination
    fun NavGraphBuilder.register()
}
