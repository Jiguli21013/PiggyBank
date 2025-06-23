package com.yanchelenko.piggybank.ui.scaffold

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
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggynank.core.ui.theme.Dimens.IconMedium

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        AppDestination.Scanner,
        AppDestination.History
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { destination ->
            val isSelected = currentRoute == destination.route

            val iconResId = when (destination) {
                AppDestination.Scanner -> AppDestination.Scanner.iconResId
                AppDestination.History -> AppDestination.History.iconResId
                else -> error("Unexpected destination in BottomNavigationBar")
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemSelected(destination.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = iconResId),
                        modifier = Modifier.size(size = IconMedium),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = destination.titleResId))
                }
            )
        }
    }
}
