package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation

import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.components.HistoryOfScansList
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryOfScansEffect
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryOfScansEvent
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.resources.R
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ConfirmDeleteDialog
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ErrorMessage
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.base.ui_kit.test.UiTestTags
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

@Composable
fun HistoryOfScansMainScreen(
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (Long) -> Unit
) {
    val viewModel: HistoryOfScansViewModel = hiltViewModel()

    HistoryOfScansMainScreen(
        viewModel = viewModel,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails
    )
}

@Composable
internal fun HistoryOfScansMainScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryOfScansViewModel,
    onNavigateToProductDetails: (Long) -> Unit
) {
    var dialogProduct by remember { mutableStateOf<ScannedProductUiModel?>(value = null) }

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
            .semantics { contentDescription = UiTestTags.HISTORY_OF_SCANS_ROOT },
        onEffect = { effect ->
            when (effect) {
                is HistoryOfScansEffect.NavigateToDetails -> onNavigateToProductDetails(effect.product.productId)
                is HistoryOfScansEffect.NavigateToDialogDeleteProduct -> { dialogProduct = effect.product }
                is HistoryOfScansEffect.ShowMessage -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                is HistoryOfScansEffect.ShowError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            HistoryOfScansMainContent(
                currentHistoryState = uiState,
                items = items,
                onEvent = sendEvent,
                modifier = innerModifier,
            )

            if (dialogProduct != null) {
                //todo call this dialog through navigation
                ConfirmDeleteDialog(
                    modifier = Modifier,
                    onConfirm = {
                        viewModel.onEvent(event = HistoryOfScansEvent.OnProductDeleted(product = dialogProduct!!))
                        dialogProduct = null
                    },
                    onDismiss = {
                        viewModel.onEvent(event = HistoryOfScansEvent.OnDeleteDialogDismissed)
                        dialogProduct = null
                    }
                )
            }
        }
    )
}

@Composable
fun HistoryOfScansMainContent(
    modifier: Modifier = Modifier,
    currentHistoryState: CommonUiState<Unit>,
    items: LazyPagingItems<ListItem>,
    onEvent: (HistoryOfScansEvent) -> Unit,
) {
    Column(modifier = modifier) {
        println("currentHistoryState --- ${currentHistoryState}")
        when (currentHistoryState) {
            is CommonUiState.Success -> {
                if (items.itemCount > 0) {
                    HistoryOfScansList(
                        items = items,
                        onEvent = onEvent,
                        modifier = modifier.semantics { contentDescription = UiTestTags.HISTORY_OF_SCANS_LIST }
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize().semantics { contentDescription = UiTestTags.HISTORY_OF_SCANS_EMPTY },
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.label_empty_for_now),
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
