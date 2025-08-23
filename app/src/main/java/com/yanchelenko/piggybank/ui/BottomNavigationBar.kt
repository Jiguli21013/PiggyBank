package com.yanchelenko.piggybank.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.yanchelenko.piggybank.NavigationUiMeta
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggynank.core.ui.theme.Dimens.IconMedium

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        AppDestination.ScannerDestination,
        AppDestination.HistoryDestination
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { destination ->
            val isSelected = currentRoute == destination.routeForRegistration

            val iconResId =
                NavigationUiMeta.resolveIconRes(currentRoute = destination.routeForRegistration)
            val titleResId =
                NavigationUiMeta.resolveTitleRes(currentRoute = destination.routeForRegistration)

            if (titleResId != null && iconResId != null) {
                val titleText = stringResource(id = titleResId)
                val icon = painterResource(id = iconResId)

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemSelected(destination.routeForRegistration) },
                    icon = {
                        Icon(
                            painter = icon,
                            contentDescription = titleText,
                            modifier = Modifier.size(IconMedium)
                        )
                    },
                    label = { Text(text = titleText) }
                )
            }
        }
    }
}
