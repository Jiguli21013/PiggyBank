package com.yanchelenko.piggybank.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.yanchelenko.piggybank.components.CenteredLoader
import com.yanchelenko.piggybank.components.ConfirmDeleteDialog
import com.yanchelenko.piggybank.components.ErrorMessage
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.mvi.UiState
import com.yanchelenko.piggybank.presentation.components.HistoryList
import com.yanchelenko.piggybank.presentation.models.ListItem
import com.yanchelenko.piggybank.presentation.state.HistoryEffect
import com.yanchelenko.piggybank.presentation.state.HistoryEvent

@Composable
fun HistoryMainScreen(
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (Long) -> Unit
) {
    val viewModel: HistoryViewModel = hiltViewModel()

    HistoryMainScreen(
        viewModel = viewModel,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails
    )
}

@Composable
internal fun HistoryMainScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel,
    onNavigateToProductDetails: (Long) -> Unit
) {
    var dialogProduct by remember { mutableStateOf<Product?>(value = null) }

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
        modifier = modifier,
        onEffect = { effect ->
            when (effect) {
                is HistoryEffect.NavigateToDetails -> onNavigateToProductDetails(effect.product.id)
                is HistoryEffect.NavigateToDialogDeleteProduct -> {
                    dialogProduct = effect.product
                }

                is HistoryEffect.ShowMessage -> Toast.makeText(
                    context,
                    effect.message,
                    Toast.LENGTH_SHORT
                ).show()

                is HistoryEffect.ShowError -> Toast.makeText(
                    context,
                    effect.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            HistoryMainContent(
                currentHistoryState = uiState,
                items = items,
                onEvent = sendEvent,
                modifier = innerModifier,
            )

            if (dialogProduct != null) {
                ConfirmDeleteDialog(
                    modifier = innerModifier,
                    onConfirm = {
                        viewModel.onEvent(event = HistoryEvent.OnProductDeleted(product = dialogProduct!!))
                        dialogProduct = null
                    },
                    onDismiss = {
                        viewModel.onEvent(event = HistoryEvent.OnDeleteDialogDismissed)
                        dialogProduct = null
                    }
                )
            }
        }
    )
}

@Composable
fun HistoryMainContent(
    modifier: Modifier = Modifier,
    currentHistoryState: UiState<Unit>,
    items: LazyPagingItems<ListItem>,
    onEvent: (HistoryEvent) -> Unit,
) {
    Column(modifier = modifier) {
        when (currentHistoryState) {
            is UiState.Success -> {
                HistoryList(
                    items = items,
                    onEvent = onEvent,
                    modifier = modifier,
                )
            }

            is UiState.Loading -> {
                CenteredLoader()
            }

            is UiState.Error -> {
                ErrorMessage(message = "some message")
            } //todo

        }
    }
}
