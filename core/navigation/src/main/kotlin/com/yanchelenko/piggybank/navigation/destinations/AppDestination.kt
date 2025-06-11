package com.yanchelenko.piggybank.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.yanchelenko.piggybank.core.ui.R

//todo interface or class?
sealed interface AppDestination : NavigationDestination {

    data object Scanner : AppDestination {
        override val route = "scanner"
        override val arguments = emptyList<NamedNavArgument>()
        override val titleResId = R.string.title_scanner

        val iconResId = R.drawable.ic_scanner_bottom_bar
    }

    data object History : AppDestination {
        override val route = "history"
        override val arguments = emptyList<NamedNavArgument>()
        override val titleResId = R.string.title_history

        val iconResId = R.drawable.ic_history_bottom_bar
    }

    data class InsertProduct(val productId: Long) : AppDestination {
        override val route = Companion.route
        override val arguments get() = Companion.arguments
        override val titleResId get() = Companion.titleResId
        override fun fullRoute() = "$route/$productId"

        companion object {
            const val route = "product_insert"
            const val routeTemplate = "$route/{barcode}"
            val arguments = listOf(navArgument(name = "barcode") { type = NavType.StringType })
            val titleResId = R.string.title_insert_product
        }
    }

    data class EditProduct(val productId: Long) : AppDestination {
        override val route = Companion.route
        override val arguments get() = Companion.arguments
        override val titleResId get() = Companion.titleResId
        override fun fullRoute() = "$route/$productId"

        companion object {
            const val route = "product_edit"
            const val routeTemplate = "$route/{productId}"
            val arguments = listOf(navArgument(name = "productId") { type = NavType.LongType })
            val titleResId = R.string.title_edit_product
        }
    }

    data class ProductDetails(val product: String) : AppDestination {
        override val route = Companion.route
        override val arguments get() = Companion.arguments
        override val titleResId get() = Companion.titleResId
        override fun fullRoute() = "$route/$product"

        companion object {
            const val route = "product_details"
            const val routeTemplate = "$route/{productJson}"
            val arguments = listOf(navArgument(name = "productJson") { type = NavType.StringType })
            val titleResId = R.string.title_product_details
        }
    }
}
