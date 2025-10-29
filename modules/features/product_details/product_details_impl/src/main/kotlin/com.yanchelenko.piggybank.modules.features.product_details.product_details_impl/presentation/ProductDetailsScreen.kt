package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
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
import com.yanchelenko.piggybank.features.product_details.presentation.components.InfoRow
import com.yanchelenko.piggybank.modules.dev_tools.RebuggerIfDebug
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsEffect
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsEvent
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ConfirmDeleteDialog
import com.yanchelenko.piggybank.modules.base.ui_kit.components.InfoCard
import com.yanchelenko.piggybank.modules.base.ui_kit.components.PrimaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.components.SecondaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.base.ui_kit.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacerHeight
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingMedium
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.R
import com.yanchelenko.piggybank.modules.base.ui_kit.animations.AnimationDurations.FAST
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.trackMap
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel

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
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel,
    onNavigateToEditProduct: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    val effectFlow = viewModel.effect

    var showDeleteDialog by remember { mutableStateOf(value = false) }
    var isContentVisible by remember { mutableStateOf(value = true) }

    // Re-fetch latest product data when the screen resumes (e.g., after editing)
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refresh()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer = observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer = observer) }
    }

    ScreenWithEffect(
        state = state,
        effectFlow = effectFlow,
        onEvent = viewModel::onEvent,
        modifier = modifier,
        onEffect = { effect ->
            when (effect) {
                is ProductDetailsEffect.NavigateToEdit -> onNavigateToEditProduct(effect.productId)
                is ProductDetailsEffect.ShowDeleteDialog -> { showDeleteDialog = true }
                is ProductDetailsEffect.CloseDeleteDialog -> { showDeleteDialog = false }
                is ProductDetailsEffect.DeletionAnimation -> { isContentVisible = false }
                is ProductDetailsEffect.NavigateBack -> onNavigateBack.invoke()
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            when (uiState) {
                is CommonUiState.Success -> {
                    if (showDeleteDialog) {
                        //todo call this dialog through navigation
                        ConfirmDeleteDialog(
                            modifier = Modifier,
                            onConfirm = { sendEvent(ProductDetailsEvent.DialogConfirmedDelete) },
                            onDismiss = { sendEvent(ProductDetailsEvent.DialogCancelDelete) }
                        )
                    }

                    AnimatedVisibility(
                        visible = isContentVisible,
                        exit = fadeOut(animationSpec = tween(durationMillis = FAST)),
                        modifier = innerModifier
                    ) {
                        ProductDetailsContent(
                            state = uiState.data,
                            modifier = innerModifier,
                            onEvent = sendEvent
                        )
                    }

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
    state: ScannedProductUiModel,
    onEvent: (ProductDetailsEvent) -> Unit
) {
    RebuggerIfDebug(trackMap = state.trackMap(), composableName = "ProductDetailsContent")

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

@Preview(showBackground = true)
@Composable
private fun ProductDetailsContentPreview(
    @PreviewParameter(ProductPreviewProvider::class)
    product: ScannedProductUiModel
) {
    PiggyBankTheme {
        ProductDetailsContent(
            state = product,
            onEvent = {}
        )
    }
}
