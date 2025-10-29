package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ConfirmDeleteDialog
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ErrorMessage
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.components.CartBottomBar
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.components.CartList
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEffect
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEvent
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartScreenState
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.R

@Composable
fun CartMainScreen(
    modifier: Modifier = Modifier,
    onNavigateToHistoryOfCarts: () -> Unit,
    onNavigateToProductOfCartDetails: (Long) -> Unit
) {
    val viewModel: CartViewModel = hiltViewModel()

    CartMainScreen(
        viewModel = viewModel,
        modifier = modifier,
        onNavigateToHistoryOfCarts = onNavigateToHistoryOfCarts,
        onNavigateToProductOfCartDetails = onNavigateToProductOfCartDetails
    )
}

@Composable
internal fun CartMainScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel,
    onNavigateToHistoryOfCarts: () -> Unit,
    onNavigateToProductOfCartDetails: (Long) -> Unit,
) {
    var dialogProduct by remember { mutableStateOf<ProductOfCartUiModel?>(value = null) }

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
            .semantics { contentDescription = "cart_root" },
        onEffect = { effect ->
            when (effect) {
                is CartEffect.NavigateToProductOfCartDetails -> onNavigateToProductOfCartDetails(effect.productOfCart.productId)
                is CartEffect.NavigateToDialogDeleteProductOfCart -> { dialogProduct = effect.productOfCart }
                is CartEffect.NavigateToHistoryOfCarts -> onNavigateToHistoryOfCarts.invoke()
                is CartEffect.ShowMessage -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                is CartEffect.ShowError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            CartMainContent(
                state = uiState,
                items = items,
                onEvent = sendEvent,
                modifier = innerModifier,
            )

            if (dialogProduct != null) {
                //todo call this dialog through navigation
                ConfirmDeleteDialog(
                    modifier = Modifier,
                    onConfirm = {
                        viewModel.onEvent(event = CartEvent.OnProductOfCartDeleted(productOfCart = dialogProduct!!))
                        dialogProduct = null
                    },
                    onDismiss = {
                        viewModel.onEvent(event = CartEvent.OnDeleteDialogDismissed)
                        dialogProduct = null
                    }
                )
            }
        }
    )
}

@Composable
fun CartMainContent(
    modifier: Modifier = Modifier,
    state: CommonUiState<CartScreenState>,
    items: LazyPagingItems<ProductOfCartUiModel>,
    onEvent: (CartEvent) -> Unit,
) {
    Column(modifier = modifier) {
        when (state) {
            is CommonUiState.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    CartList(
                        items = items,
                        onEvent = onEvent,
                        modifier = Modifier.weight(1f) //todo тут modifier или Modifier
                    )

                    if (items.itemCount > 0) {
                        CartBottomBar(
                            state = state.data,
                            onEvent = onEvent,
                            modifier = Modifier.fillMaxWidth() //todo тут modifier или Modifier
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.cart_empty_message),
                                style = typography.bodyLarge,
                                color = colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                        }
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
                ErrorMessage(message = "some message") //todo
            }

        }
    }
}
