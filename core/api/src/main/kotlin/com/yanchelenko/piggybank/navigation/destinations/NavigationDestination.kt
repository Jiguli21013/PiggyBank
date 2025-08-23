package com.yanchelenko.piggybank.navigation.destinations

import androidx.navigation.NamedNavArgument

interface NavigationDestination {
    val routeForRegistration: String
    val arguments: List<NamedNavArgument>
}
