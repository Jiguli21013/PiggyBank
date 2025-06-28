package com.yanchelenko.piggybank.features.product_details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.common.ui_preview.ProductPreviewProvider
import com.yanchelenko.piggybank.common.ui_state.CommonUiState
import com.yanchelenko.piggybank.core.debugUI.debug.WithDebug
import com.yanchelenko.piggybank.core.debugUI.debug.trackMap
import com.yanchelenko.piggybank.features.product_details.R
import com.yanchelenko.piggybank.features.product_details.presentation.components.InfoRow
import com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect
import com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent
import com.yanchelenko.piggybank.common.ui.CenteredLoader
import com.yanchelenko.piggybank.common.ui.ConfirmDeleteDialog
import com.yanchelenko.piggynank.core.ui.components.InfoCard
import com.yanchelenko.piggynank.core.ui.components.PrimaryButton
import com.yanchelenko.piggynank.core.ui.components.SecondaryButton
import com.yanchelenko.piggynank.core.ui.effect.ScreenWithEffect
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacerHeight
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacingMedium
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigateToEditProduct: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    ProductDetailsScreen(
        modifier = modifier,
        viewModel = hiltViewModel(),
        onNavigateToEditProduct = { onNavigateToEditProduct.invoke(it) },
        onNavigateBack = { onNavigateBack.invoke() }
    )
}

@Composable
internal fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel, // viewModel
    modifier: Modifier = Modifier,
    onNavigateToEditProduct: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    val effectFlow = viewModel.effect
    var showDeleteDialog by remember { mutableStateOf(value = false) }

    ScreenWithEffect(
        state = state,
        effectFlow = effectFlow,
        onEvent = viewModel::onEvent,
        modifier = modifier,
        onEffect = { effect ->
            when (effect) {
                is ProductDetailsEffect.NavigateToEdit -> onNavigateToEditProduct(effect.productId)
                is ProductDetailsEffect.NavigateBack -> { onNavigateBack.invoke() }
                is ProductDetailsEffect.ShowDeleteDialog -> { showDeleteDialog = true }
                is ProductDetailsEffect.CloseDeleteDialog -> { showDeleteDialog = false }
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            when (uiState) {
                is CommonUiState.Success -> {
                    if (showDeleteDialog) {
                        //todo вызывать через навигацию
                        ConfirmDeleteDialog(
                            onConfirm = { sendEvent(ProductDetailsEvent.ConfirmedDelete) },
                            onDismiss = { sendEvent(ProductDetailsEvent.CancelDelete) }
                        )
                    }
                    ProductDetailsContent(
                        state = uiState.data,
                        modifier = innerModifier,
                        onEvent = sendEvent
                    )
                }
                is CommonUiState.Initializing -> {
                    CenteredLoader()
                }
                is CommonUiState.Loading -> {
                    CenteredLoader()
                }
                is CommonUiState.Error -> {
                    //todo
                }
            }
        }
    )
}

@Composable
private fun ProductDetailsContent(
    modifier: Modifier = Modifier,
    state: ProductUiModel,
    onEvent: (ProductDetailsEvent) -> Unit
) {
    val barcodeLabel = stringResource(R.string.label_barcode)
    val nameLabel = stringResource(R.string.label_product_name)
    val weightLabel = stringResource(R.string.label_weight)
    val priceLabel = stringResource(R.string.label_price)
    val pricePerKgLabel = stringResource(R.string.label_price_per_kg)
    val editText = stringResource(R.string.action_edit)
    val deleteText = stringResource(R.string.action_delete)

    val weightValue = stringResource(R.string.format_grams, state.weight)
    val priceValue = stringResource(R.string.format_price_rub, state.price)
    val pricePerKgValue = stringResource(R.string.format_price_rub, state.pricePerKg)

    WithDebug(
        trackMap = state.trackMap(),
        composableName = "ProductDetailsContent"
    ) {
        Column(
            modifier = modifier.padding(all = PaddingMedium)
        ) {
            InfoCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(label = barcodeLabel, value = state.barcode)
                InfoRow(label = nameLabel, value = state.productName)
                InfoRow(label = weightLabel, value = weightValue)
                InfoRow(label = priceLabel, value = priceValue)
                InfoRow(label = pricePerKgLabel, value = pricePerKgValue)
            }

            Spacer(modifier = Modifier.height(height = SpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space = SpacingMedium)
            ) {
                PrimaryButton(
                    text = editText,
                    onClick = { onEvent(ProductDetailsEvent.OnEditClicked) },
                    modifier = Modifier.weight(1f)
                )
                SecondaryButton(
                    text = deleteText,
                    onClick = { onEvent(ProductDetailsEvent.OnDeleteClicked) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsContentPreview(
    @PreviewParameter(ProductPreviewProvider::class) product: ProductUiModel
) {
    PiggyBankTheme {
        ProductDetailsContent(
            state = product,
            onEvent = {}
        )
    }
}
