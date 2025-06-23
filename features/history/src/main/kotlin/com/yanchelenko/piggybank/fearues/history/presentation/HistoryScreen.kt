package com.yanchelenko.piggybank.fearues.history.presentation

import com.yanchelenko.piggybank.fearues.history.presentation.components.HistoryList
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
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.common.ui_state.CommonUiState
import com.yanchelenko.piggybank.fearues.history.presentation.models.ListItem
import com.yanchelenko.piggybank.common.ui.CenteredLoader
import com.yanchelenko.piggybank.common.ui.ConfirmDeleteDialog
import com.yanchelenko.piggybank.common.ui.ErrorMessage
import com.yanchelenko.piggynank.core.ui.effect.ScreenWithEffect

@Composable
fun HistoryMainScreen(
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (ProductUiModel) -> Unit
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
    onNavigateToProductDetails: (ProductUiModel) -> Unit
) {
    var dialogProduct by remember { mutableStateOf<ProductUiModel?>(null) }
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
                is HistoryEffect.NavigateToDetails -> onNavigateToProductDetails(effect.product)
                is HistoryEffect.NavigateToDialogDeleteProduct -> { dialogProduct = effect.product }
                is HistoryEffect.ShowError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            HistoryMainContent(
                currentHistoryState = uiState,
                items = items,
                modifier = innerModifier,
                onEvent = sendEvent
            )

            if (dialogProduct != null) {
                ConfirmDeleteDialog(
                    onConfirm = {
                        viewModel.onEvent(HistoryEvent.OnProductDeleted(product = dialogProduct!!))
                        dialogProduct = null
                    },
                    onDismiss = {
                        viewModel.onEvent(HistoryEvent.OnDeleteDialogDismissed)
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
    currentHistoryState: CommonUiState<Unit>,
    items: LazyPagingItems<ListItem>,
    onEvent: (HistoryEvent) -> Unit
) {
    Column(modifier = modifier) {
        when (currentHistoryState) {
            is CommonUiState.Success -> {
                HistoryList(
                    items = items,
                    modifier = modifier,
                    onEvent = onEvent
                )
            }
            is CommonUiState.Initializing -> { CenteredLoader() }
            is CommonUiState.Loading -> { CenteredLoader() }
            is CommonUiState.Error -> { ErrorMessage(message = "some message" ) } //todo

        }
    }
}
