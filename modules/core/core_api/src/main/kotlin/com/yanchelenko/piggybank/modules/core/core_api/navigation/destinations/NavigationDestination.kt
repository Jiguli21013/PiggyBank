package com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations

import androidx.navigation.NamedNavArgument

interface NavigationDestination {
    val routeForRegistration: String
    val arguments: List<NamedNavArgument>
}
