package com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface AppDestination : NavigationDestination {

    // ---- Bottom Bar Screens ----
    data object ScannerDestination : AppDestination {
        override val routeForRegistration = "scanner"
        override val arguments = emptyList<NamedNavArgument>()
        fun fullRoute(): String = "scanner"
    }

    data object HistoryDestination : AppDestination {
        override val routeForRegistration = "history"
        override val arguments = emptyList<NamedNavArgument>()
        fun fullRoute(): String = "history"
    }

    // ---- Parameterized Destinations ----
    data class InsertProductDestination(val barcode: String) : AppDestination {
        override val routeForRegistration = Meta.routeTemplate
        override val arguments get() = Meta.arguments
        fun fullRoute(): String = "product_insert/$barcode"

        object Meta : NavigationDestination {
            const val routeTemplate = "product_insert/{barcode}"
            override val routeForRegistration = routeTemplate
            override val arguments = listOf(
                navArgument("barcode") { type = NavType.StringType }
            )
        }
    }

    data class ProductEditDestination(val productId: Long) : AppDestination {
        override val routeForRegistration = Meta.routeTemplate
        override val arguments get() = Meta.arguments
        fun fullRoute(): String = "product_edit/$productId"

        object Meta : NavigationDestination {
            const val routeTemplate = "product_edit/{productId}"
            override val routeForRegistration = routeTemplate
            override val arguments = listOf(
                navArgument("productId") { type = NavType.LongType }
            )
        }
    }

    data class ProductDetailsDestination(val productId: Long) : AppDestination {
        override val routeForRegistration = Meta.routeTemplate
        override val arguments get() = Meta.arguments
        fun fullRoute(): String = "product_details/$productId"

        object Meta : NavigationDestination {
            const val routeTemplate = "product_details/{productId}"
            override val routeForRegistration = routeTemplate
            override val arguments = listOf(
                navArgument("productId") { type = NavType.LongType }
            )
        }
    }
}
