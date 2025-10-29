package com.yanchelenko.piggybank.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.yanchelenko.piggybank.modules.base.ui_model.navigation.NavigationUiMeta
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.IconMedium

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onItemSelected: (String) -> Unit,
    cartItemsCount: Int = 0
) {
    val items = listOf(
        AppDestination.ScannerDestination,
        AppDestination.HistoryOfScansDestination,
        AppDestination.HistoryOfCartsDestination,
        AppDestination.CartDestination
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { destination ->
            val isSelected = currentRoute == destination.routeForRegistration

            val iconResId = NavigationUiMeta.resolveIconRes(currentRoute = destination.routeForRegistration)
            val titleResId = NavigationUiMeta.resolveTitleRes(currentRoute = destination.routeForRegistration)

            if (titleResId != null && iconResId != null) {
                val titleText = stringResource(id = titleResId)
                val icon = painterResource(id = iconResId)

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemSelected(destination.routeForRegistration) },
                    icon = {
                        val baseModifier = Modifier
                            .size(size = IconMedium)
                            .semantics { contentDescription = "nav_${destination.routeForRegistration}" }

                        val countForDestination = if (destination == AppDestination.CartDestination) cartItemsCount else 0

                        if (destination == AppDestination.CartDestination && countForDestination > 0) {
                            NavIconWithOptionalBadge(
                                icon = icon,
                                contentDescription = titleText,
                                count = countForDestination,
                                modifier = baseModifier
                            )
                        } else {
                            Icon(
                                painter = icon,
                                contentDescription = titleText,
                                modifier = baseModifier
                            )
                        }
                    },
                    label = { Text(text = titleText) }
                )
            }
        }
    }
}

@Composable
private fun NavIconWithOptionalBadge(
    icon: Painter,
    contentDescription: String,
    count: Int?,
    modifier: Modifier = Modifier
) {
    val capped = count?.let { if (it > 99) 99 else it }
    if (capped != null && capped > 0) {
        BadgedBox(
            badge = {
                Badge {
                    Text(text = if (capped == 99) "99+" else capped.toString())
                }
            }
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                modifier = modifier
            )
        }
    } else {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            modifier = modifier
        )
    }
}
