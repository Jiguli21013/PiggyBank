package com.yanchelenko.piggybank.ui.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yanchelenko.piggybank.navigation.destinations.ScreenMeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    screenMeta: ScreenMeta,
    currentRoute: String?,
    onNavigationSelected: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val title = stringResource(id = screenMeta.titleResId)

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = title) }) },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemSelected = onNavigationSelected
            )
        },
        content = content
    )
}
