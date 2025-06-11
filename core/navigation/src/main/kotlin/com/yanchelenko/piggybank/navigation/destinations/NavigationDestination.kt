package com.yanchelenko.piggybank.navigation.destinations

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument

interface NavigationDestination {
    val route: String
    val arguments: List<NamedNavArgument>
    @get:StringRes
    val titleResId: Int
    fun fullRoute(): String = route
}
