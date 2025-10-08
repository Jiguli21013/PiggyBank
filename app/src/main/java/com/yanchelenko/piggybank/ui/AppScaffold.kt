package com.yanchelenko.piggybank.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yanchelenko.piggybank.modules.base.ui_model.navigation.NavigationUiMeta
import com.yanchelenko.piggybank.modules.dev_tools.fps.FpsOverlay
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium

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
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                TopAppBar(title = { Text(text = title) })
                FpsOverlay(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .matchParentSize()
                        .padding(all = PaddingMedium)
                )
            }
                 },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemSelected = onNavigationSelected
            )
        },
        content = content
    )
}
