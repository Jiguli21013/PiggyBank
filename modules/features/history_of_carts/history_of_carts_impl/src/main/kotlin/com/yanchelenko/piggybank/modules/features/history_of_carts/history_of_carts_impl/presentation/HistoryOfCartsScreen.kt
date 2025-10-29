package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ErrorMessage
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.components.HistoryOfCartsList
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.state.HistoryOfCartsEffect
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.state.HistoryOfCartsEvent
import com.yanchelenko.piggybank.modules.base.resources.R as BaseR

@Composable
fun HistoryOfCartsMainScreen(
    modifier: Modifier = Modifier,
    onNavigateToCartDetails: (Long) -> Unit
) {
    val viewModel: HistoryOfCartsViewModel = hiltViewModel()

    HistoryOfCartsMainScreen(
        viewModel = viewModel,
        modifier = modifier,
        onNavigateToCartDetails = onNavigateToCartDetails
    )
}

@Composable
internal fun HistoryOfCartsMainScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryOfCartsViewModel,
    onNavigateToCartDetails: (Long) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

    val pagingFlow = remember { viewModel.pagedItems() }
    val items = pagingFlow.collectAsLazyPagingItems()

    LaunchedEffect(items.loadState) {
        viewModel.onLoadStateChanged(
            loadState = items.loadState,
            itemCount = items.itemCount
        )
    }

    ScreenWithEffect(
        state = state,
        effectFlow = effectFlow,
        onEvent = viewModel::onEvent,
        modifier = modifier
            .fillMaxSize()
            .semantics { contentDescription = "history_of_carts_root" },
        onEffect = { effect ->
            when (effect) {
                is HistoryOfCartsEffect.NavigateToCartDetails -> { } //onNavigateToCartDetails(effect.cart.cartId)

                is HistoryOfCartsEffect.ShowMessage -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                is HistoryOfCartsEffect.ShowError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            HistoryOfCartsMainContent(
                currentHistoryState = uiState,
                items = items,
                onEvent = sendEvent,
                modifier = innerModifier,
            )
        }
    )
}

@Composable
fun HistoryOfCartsMainContent(
    modifier: Modifier = Modifier,
    currentHistoryState: CommonUiState<Unit>,
    items: LazyPagingItems<ListItem>,
    onEvent: (HistoryOfCartsEvent) -> Unit,
) {
    Column(modifier = modifier) {
        when (currentHistoryState) {
            is CommonUiState.Success -> {
                if (items.itemCount > 0) {
                    HistoryOfCartsList(
                        items = items,
                        onEvent = onEvent,
                        modifier = modifier
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(
                            text = stringResource(BaseR.string.label_empty_for_now),
                            style = typography.bodyLarge,
                            color = colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            is CommonUiState.Initializing -> {
                CenteredLoader()
            }
            is CommonUiState.Loading -> {
                CenteredLoader()
            }
            is CommonUiState.Error -> {
                ErrorMessage(message = "some message")
            } //todo

        }
    }
}
