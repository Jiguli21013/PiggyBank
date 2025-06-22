package com.yanchelenko.piggybank.navigation.registry

import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.navigation.destinations.ScreenMeta

object ScreenRegistry {

    private val screens = listOf(
        ScreenMeta(
            routeTemplate = AppDestination.Scanner.route,
            titleResId = AppDestination.Scanner.titleResId
        ),
        ScreenMeta(
            routeTemplate = AppDestination.History.route,
            titleResId = AppDestination.History.titleResId
        ),
        ScreenMeta(
            routeTemplate = AppDestination.InsertProduct.routeTemplate,
            titleResId = AppDestination.InsertProduct.titleResId
        ),
        ScreenMeta(
            routeTemplate = AppDestination.EditProduct.routeTemplate,
            titleResId = AppDestination.EditProduct.titleResId
        ),
        ScreenMeta(
            routeTemplate = AppDestination.ProductDetails.routeTemplate,
            titleResId = AppDestination.ProductDetails.titleResId
        )
    )

    private val ROUTE_PLACEHOLDER_REGEX = Regex(pattern = "\\{[^}]+\\}")
    private const val ROUTE_REPLACEMENT = ".*"

    fun resolveScreenMeta(
        currentRoute: String?,
    ): ScreenMeta {
        if (currentRoute == null) return ScreenMeta("", android.R.string.unknownName)
        return screens.firstOrNull { meta ->
            val regexPattern = meta.routeTemplate.replace(
                regex = ROUTE_PLACEHOLDER_REGEX,
                replacement = ROUTE_REPLACEMENT
            )
            Regex(pattern = "^$regexPattern$").matches(input = currentRoute)
        } ?: ScreenMeta("", android.R.string.unknownName)
    }
}
