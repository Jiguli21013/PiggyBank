package com.yanchelenko.piggybank.features.product_insert.presentation

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
import com.yanchelenko.piggybank.common.ui_preview.ProductPreviewProvider
import com.yanchelenko.piggybank.common.ui_state.CommonUiState
import com.yanchelenko.piggybank.core.debugUI.debug.WithDebug
import com.yanchelenko.piggybank.features.product_insert.R
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEffect
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent
import com.yanchelenko.piggynank.core.ui.dimens.LocalDimens
import com.yanchelenko.piggynank.core.ui.effect.ScreenWithEffect
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelenko.piggybank.core.debugUI.debug.trackMap
import com.yanchelenko.piggynank.core.ui.components.CenteredLoader

@Composable
fun InsertProductMainScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    val viewModel: InsertProductScreenViewModel = hiltViewModel()
    InsertProductMainScreen(
        viewModel = viewModel,
        modifier = modifier,
        onNavigateBack = onNavigateBack
    )
}

@Composable
internal fun InsertProductMainScreen(
    viewModel: InsertProductScreenViewModel,
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
                is InsertProductEffect.ShowMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is InsertProductEffect.NavigateBackToScanner -> {
                    onNavigateBack()
                }
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            when (uiState) {
                is CommonUiState.Success -> {
                    InsertProductContent(
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
fun InsertProductContent(
    state: ProductUiModel,
    modifier: Modifier = Modifier,
    onEvent: (InsertProductEvent) -> Unit
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
        composableName = "InsertProductContent" //todo
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(all = dimens.screenPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = dimens.sectionSpacing)
            ) {
                TextField(
                    value = state.productName,
                    onValueChange = { value ->
                        onEvent(InsertProductEvent.ProductNameChanged(name = value))
                    },
                    label = { Text(productNameLabel) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = state.weight.formatIfNonZero(),
                    onValueChange = { value ->
                        value.toDoubleOrNull()?.let {
                            onEvent(InsertProductEvent.WeightChanged(weight = it))
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
                            onEvent(InsertProductEvent.PriceChanged(price = it))
                        }
                    },
                    label = { Text(text = priceLabel) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = state.pricePerKg.formatIfNonZero(),
                    onValueChange = {},
                    label = { Text(text = pricePerKgLabel) },
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
                horizontalArrangement = Arrangement.spacedBy(space = dimens.buttonSpacing),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onEvent(InsertProductEvent.GoBackToScanner) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = backText)
                }

                Button(
                    onClick = { onEvent(InsertProductEvent.SaveProduct) },
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
    @PreviewParameter(ProductPreviewProvider::class) product: ProductUiModel
) {
    PiggyBankTheme {
        InsertProductContent(
            state = product,
            onEvent = {}
        )
    }
}
