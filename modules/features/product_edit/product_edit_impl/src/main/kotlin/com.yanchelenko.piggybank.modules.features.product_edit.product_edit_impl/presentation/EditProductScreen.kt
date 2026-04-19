package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanchelenko.piggybank.modules.dev_tools.RebuggerIfDebug
import com.yanchelenko.piggybank.modules.base.infrastructure.extensions.formatIfNonZero
import com.yanchelenko.piggybank.modules.base.ui_kit.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductEffect
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductEvent
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductState
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.R
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_kit.components.fields.OutlinedInputField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.buttons.PrimaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.components.fields.ReadOnlyField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.buttons.SecondaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.components.fields.PriceInputField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.fields.WeightInputField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ChangedValueHint
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingExtraLarge
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.base.resources.R as BaseR

@Composable
fun EditProductScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    val viewModel: EditProductViewModel = hiltViewModel()

    EditProductScreen(
        viewModel = viewModel,
        modifier = modifier,
        onNavigateBack = onNavigateBack
    )
}

@Composable
internal fun EditProductScreen(
    modifier: Modifier = Modifier,
    viewModel: EditProductViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

    ScreenWithEffect(
        state = state,
        effectFlow = effectFlow,
        onEvent = viewModel::onEvent,
        modifier = modifier,
        onEffect = { effect ->
            when (effect) {
                is EditProductEffect.ShowMessage -> {
                    Toast.makeText(
                        context,
                        effect.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is EditProductEffect.NavigateBack -> { onNavigateBack.invoke() }
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            when (uiState) {
                is CommonUiState.Success -> {
                    EditProductContent(
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
fun EditProductContent(
    modifier: Modifier = Modifier,
    state: EditProductState,
    onEvent: (EditProductEvent) -> Unit
) {
    RebuggerIfDebug(trackMap = state.trackMap(), composableName = "EditProductContent")

    val productNameLabel = stringResource(R.string.label_product_name)
    val priceLabel = stringResource(R.string.label_price_by_weight, state.scannedProduct.weight)
    val pricePerKgLabel = stringResource(R.string.label_price_per_kg)
    val backText = stringResource(R.string.action_back)
    val saveText = stringResource(R.string.action_save)

    val previousPriceLabel = stringResource(BaseR.string.label_previous_price)
    val previousWeightLabel = stringResource(BaseR.string.label_previous_weight)

    val previousPriceValue =
        state.previousPrice?.let { "$it" } ?: "—"

    val previousWeightValue =
        state.previousWeight?.let { "$it " + stringResource(BaseR.string.unit_gram)  } ?: "—"

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingMedium),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SpacingExtraLarge)
        ) {
            OutlinedInputField(
                value = state.scannedProduct.productName,
                onValueChange = { onEvent(EditProductEvent.ProductNameChanged(it)) },
                label = productNameLabel,
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            WeightInputField(
                weight = state.scannedProduct.weight,
                onWeightChange = { grams -> onEvent(EditProductEvent.WeightChanged(weight = grams)) },
                modifier = Modifier.fillMaxWidth()
            )

            if (state.hasWeightChanged) {
                ChangedValueHint(
                    label = previousWeightLabel,
                    value = previousWeightValue,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            PriceInputField(
                value = state.priceInput,
                onTextChange = { onEvent(EditProductEvent.PriceInputChanged(it)) },
                onPriceChange = { price -> onEvent(EditProductEvent.PriceChanged(price)) },
                label = priceLabel,
                modifier = Modifier.fillMaxWidth()
            )

            if (state.hasPriceChanged) {
                ChangedValueHint(
                    label = previousPriceLabel,
                    value = previousPriceValue,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            ReadOnlyField(
                label = pricePerKgLabel,
                value = state.scannedProduct.formattedPricePerKg
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(PaddingMedium),
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(modifier = Modifier.fillMaxWidth())

            Row(
                horizontalArrangement = Arrangement.spacedBy(SpacingMedium),
                modifier = Modifier.fillMaxWidth()
            ) {
                SecondaryButton(
                    text = backText,
                    onClick = { onEvent(EditProductEvent.GoBackToScanner) },
                    modifier = Modifier.weight(1f)
                )

                PrimaryButton(
                    text = saveText,
                    onClick = { onEvent(EditProductEvent.SaveProduct) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditProductContentPreview(
    @PreviewParameter(ProductPreviewProvider::class)
    product: ScannedProductUiModel
) {
    PiggyBankTheme {
        EditProductContent(
            state = EditProductState(
                scannedProduct = product,
                priceInput = product.price.formatIfNonZero()
            ),
            onEvent = {}
        )
    }
}
