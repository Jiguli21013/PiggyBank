package com.yanchelenko.piggybank.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.yanchelenko.piggybank.modules.base.ui_kit.NavigationUiMeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    currentRoute: String?,
    onNavigationSelected: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    val titleResId = remember(currentRoute) {
        NavigationUiMeta.resolveTitleRes(currentRoute)
    }

    val title = titleResId?.let { stringResource(it) }.orEmpty()

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
