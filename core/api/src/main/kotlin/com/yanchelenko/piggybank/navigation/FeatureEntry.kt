package com.yanchelenko.piggybank.navigation

import androidx.navigation.NavGraphBuilder
import com.yanchelenko.piggybank.navigation.destinations.NavigationDestination

interface FeatureEntry {
    val destination: NavigationDestination
    fun NavGraphBuilder.register()
}
