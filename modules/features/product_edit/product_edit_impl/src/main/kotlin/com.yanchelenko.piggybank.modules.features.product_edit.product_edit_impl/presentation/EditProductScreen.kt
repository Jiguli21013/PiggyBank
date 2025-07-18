package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.theapache64.rebugger.Rebugger
import com.yanchelenko.piggybank.modules.base.infrastructure.extensions.formatIfNonZero
import com.yanchelenko.piggybank.modules.base.ui_kit.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductEffect
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductEvent
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.R
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_kit.components.OutlinedInputField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.PrimaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ReadOnlyField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.SecondaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.base.ui_model.mapper.trackMap
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacerHeight
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacingExtraLarge
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacingMedium
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme

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
    state: ProductUiModel,
    onEvent: (EditProductEvent) -> Unit
) {
    val productNameLabel = stringResource(R.string.label_product_name)
    val weightLabel = stringResource(R.string.label_weight_grams)
    val priceLabel = stringResource(R.string.label_price_by_weight, state.weight)
    val pricePerKgLabel = stringResource(R.string.label_price_per_kg)
    val backText = stringResource(R.string.action_back)
    val saveText = stringResource(R.string.action_save)

    Rebugger(trackMap = state.trackMap(), composableName = "EditProductContent")

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
                value = state.productName,
                onValueChange = { onEvent(EditProductEvent.ProductNameChanged(it)) },
                label = productNameLabel,
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedInputField(
                value = state.weight.formatIfNonZero(),
                onValueChange = {
                    it.toDoubleOrNull()?.let { weight ->
                        onEvent(EditProductEvent.WeightChanged(weight))
                    }
                },
                label = weightLabel,
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedInputField(
                value = state.price.formatIfNonZero(),
                onValueChange = {
                    it.toDoubleOrNull()?.let { price ->
                        onEvent(EditProductEvent.PriceChanged(price))
                    }
                },
                label = priceLabel,
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth()
            )

            ReadOnlyField(
                label = pricePerKgLabel,
                value = state.pricePerKg.formatIfNonZero()
            )
        }

        Spacer(modifier = Modifier.height(SpacerHeight))

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

@Preview(showBackground = true)
@Composable
private fun InsertProductMainContentPreview(
    @PreviewParameter(ProductPreviewProvider::class)
    product: ProductUiModel
) {
    PiggyBankTheme {
        EditProductContent(
            state = product,
            onEvent = {}
        )
    }
}
