package com.yanchelenko.piggybank.modules.base.ui_model.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yanchelenko.piggybank.modules.base.ui_model.R

object NavigationUiMeta {

    private data class Meta(
        @StringRes val titleResId: Int,
        @DrawableRes val iconResId: Int? = null
    )

    private val routeMeta = mapOf(
        "scanner" to Meta(
            titleResId = R.string.title_scanner,
            iconResId = R.drawable.ic_scanner_bottom_bar
        ),
        "history_of_scans" to Meta(
            titleResId = R.string.title_history_of_scans,
            iconResId = R.drawable.ic_history_of_scans_bottom_bar
        ),
        "history_of_carts" to Meta(
            titleResId = R.string.title_history_of_carts,
            iconResId = R.drawable.ic_history_of_carts_bottom_bar
        ),
        "cart" to Meta(
            titleResId = R.string.title_cart,
            iconResId = R.drawable.ic_cart_bottom_bar
        ),
        "product_insert" to Meta(R.string.title_insert_product),
        "product_edit" to Meta(R.string.title_edit_product),
        "product_details" to Meta(R.string.title_product_details)
    )

    private fun resolveKey(currentRoute: String?): String? {
        if (currentRoute == null) return null
        return routeMeta.keys.firstOrNull { key -> currentRoute.startsWith(key) }
    }

    @StringRes
    fun resolveTitleRes(currentRoute: String?): Int? =
        resolveKey(currentRoute)?.let { routeMeta[it]?.titleResId }

    @DrawableRes
    fun resolveIconRes(currentRoute: String?): Int? =
        resolveKey(currentRoute)?.let { routeMeta[it]?.iconResId }
}
