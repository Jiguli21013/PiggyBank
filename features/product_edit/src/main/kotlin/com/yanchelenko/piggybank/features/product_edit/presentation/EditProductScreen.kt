package com.yanchelenko.piggybank.features.product_edit.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanchelenko.piggybank.common.extensions.formatIfNonZero
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.core.debugUI.debug.WithDebug
import com.yanchelenko.piggybank.core.debugUI.debug.trackMap
import com.yanchelenko.piggybank.features.product_edit.R
import com.yanchelenko.piggybank.features.product_edit.presentation.preview.EditProductPreviewProvider
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEffect
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductUiState
import com.yanchelenko.piggynank.core.ui.dimens.LocalDimens
import com.yanchelenko.piggynank.core.ui.effect.ScreenWithEffect
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
    viewModel: EditProductViewModel,
    modifier: Modifier = Modifier,
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
            EditProductContent(
                state = uiState.uiProduct,
                modifier = innerModifier,
                onEvent = sendEvent
            )
        }
    )
}

@Composable
fun EditProductContent(
    state: ProductUiModel,
    modifier: Modifier = Modifier,
    onEvent: (EditProductEvent) -> Unit
) {
    val dimens = LocalDimens.current

    val productNameLabel = stringResource(R.string.label_product_name)
    val weightLabel = stringResource(R.string.label_weight_grams)
    val priceLabel = stringResource(R.string.label_price_by_weight, state.weight)
    val pricePerKgLabel = stringResource(R.string.label_price_per_kg)
    val backText = stringResource(R.string.action_back)
    val saveText = stringResource(R.string.action_save)

    WithDebug(
        trackMap = state.trackMap(),
        composableName = "EditProductContent"
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(dimens.screenPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimens.sectionSpacing)
            ) {
                TextField(
                    value = state.productName,
                    onValueChange = { value ->
                        onEvent(EditProductEvent.ProductNameChanged(name = value))
                    },
                    label = { Text(text = productNameLabel) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = state.weight.formatIfNonZero(),
                    onValueChange = { value ->
                        value.toDoubleOrNull()?.let {
                            onEvent(EditProductEvent.WeightChanged(weight = it))
                        }
                    },
                    label = { Text(text = weightLabel) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = state.price.formatIfNonZero(),
                    onValueChange = { value ->
                        value.toDoubleOrNull()?.let {
                            onEvent(EditProductEvent.PriceChanged(price = it))
                        }
                    },
                    label = { Text(text = priceLabel) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = state.pricePerKg.formatIfNonZero(),
                    onValueChange = {},
                    label = { Text(pricePerKgLabel) },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    enabled = false,
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        fontSize = dimens.textLarge
                    )
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(dimens.buttonSpacing),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onEvent(EditProductEvent.GoBackToScanner) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = backText)
                }

                Button(
                    onClick = { onEvent(EditProductEvent.SaveProduct) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = saveText)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun InsertProductMainContentPreview(
    @PreviewParameter(EditProductPreviewProvider::class)
    state: EditProductUiState
) {
    PiggyBankTheme {
        EditProductContent(
            state = state.uiProduct,
            onEvent = {}
        )
    }
}
