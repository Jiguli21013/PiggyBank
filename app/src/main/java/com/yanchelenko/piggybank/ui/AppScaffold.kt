package com.yanchelenko.piggybank.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.yanchelenko.piggybank.modules.base.ui_model.navigation.NavigationUiMeta
import com.yanchelenko.piggybank.modules.dev_tools.fps.FpsOverlay
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.resources.R
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    currentRoute: String?,
    cartItemsCount: Int,
    onBottomNavigationSelected: (String) -> Unit,
    onSettingsClickClicked: () -> Unit,
    onNavigateBack: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    val titleResId = remember(currentRoute) {
        NavigationUiMeta.resolveTitleRes(currentRoute = currentRoute)
    }

    val title = titleResId?.let { stringResource(id = it) }.orEmpty()

    val showSettingsIcon = currentRoute in setOf(
        AppDestination.CartDestination.fullRoute(),
        AppDestination.ScannerDestination.fullRoute(),
        AppDestination.HistoryOfCartsDestination.fullRoute(),
        AppDestination.HistoryOfScansDestination.fullRoute()
    )

    val showBackButton = remember(currentRoute) {
        NavigationUiMeta.shouldShowBackButton(currentRoute = currentRoute)
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = { Text(text = title) },
                    navigationIcon = {
                        if (showBackButton) {
                            IconButton(onClick = onNavigateBack) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.btn_navigation_back)
                                )
                            }
                        }
                    },
                    actions = {
                        if (showSettingsIcon) {
                            IconButton(onClick = onSettingsClickClicked) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_settings),
                                    contentDescription = stringResource(R.string.settings_description)
                                )
                            }
                        }
                    }
                )
                FpsOverlay(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterEnd)
                        .matchParentSize()
                        .padding(all = PaddingMedium)
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                cartItemsCount = cartItemsCount,
                onItemSelected = onBottomNavigationSelected,
            )
        },
        content = content
    )
}
