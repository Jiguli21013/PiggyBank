package com.yanchelenko.piggybank.presentation

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
import com.yanchelenko.piggybank.components.CenteredLoader
import com.yanchelenko.piggybank.components.OutlinedInputField
import com.yanchelenko.piggybank.components.PrimaryButton
import com.yanchelenko.piggybank.components.ReadOnlyField
import com.yanchelenko.piggybank.components.SecondaryButton
import com.yanchelenko.piggybank.extensions.formatIfNonZero
import com.yanchelenko.piggybank.features.product.insert.impl.R
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.models.trackMap
import com.yanchelenko.piggybank.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.mvi.UiState
import com.yanchelenko.piggybank.presentation.state.InsertProductEffect
import com.yanchelenko.piggybank.presentation.state.InsertProductEvent
import com.yanchelenko.piggybank.preview.ProductPreviewProvider
import com.yanchelenko.piggynank.core.ui.theme.Dimens.HeaderHeight
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacingExtraLarge
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacingMedium
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme

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
    modifier: Modifier = Modifier,
    viewModel: InsertProductScreenViewModel,
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
                is UiState.Success -> {
                    InsertProductContent(
                        state = uiState.data,
                        modifier = innerModifier,
                        onEvent = sendEvent
                    )
                }

                is UiState.Loading -> {
                    CenteredLoader()
                }

                is UiState.Error -> {
                    //todo
                }
            }
        }
    )
}

@Composable
fun InsertProductContent(
    state: Product,
    modifier: Modifier = Modifier,
    onEvent: (InsertProductEvent) -> Unit
) {
    val productNameLabel = stringResource(R.string.label_product_name)
    val weightLabel = stringResource(R.string.label_weight_grams)
    val priceLabel = stringResource(R.string.label_price_by_weight, state.weight)
    val pricePerKgLabel = stringResource(R.string.label_price_per_kg)
    val backText = stringResource(R.string.action_back)
    val saveText = stringResource(R.string.action_save)

    Rebugger(trackMap = state.trackMap(), composableName = "InsertProductContent")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingMedium),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(SpacingExtraLarge)) {
            OutlinedInputField(
                value = state.productName,
                onValueChange = { onEvent(InsertProductEvent.ProductNameChanged(it)) },
                label = productNameLabel,
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedInputField(
                value = state.weight.formatIfNonZero(),
                onValueChange = {
                    it.toDoubleOrNull()?.let { weight ->
                        onEvent(InsertProductEvent.WeightChanged(weight))
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
                        onEvent(InsertProductEvent.PriceChanged(price))
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

        Spacer(modifier = Modifier.height(HeaderHeight))

        Row(
            horizontalArrangement = Arrangement.spacedBy(SpacingMedium),
            modifier = Modifier.fillMaxWidth()
        ) {
            SecondaryButton(
                text = backText,
                onClick = { onEvent(InsertProductEvent.GoBackToScanner) },
                modifier = Modifier.weight(1f)
            )

            PrimaryButton(
                text = saveText,
                onClick = { onEvent(InsertProductEvent.SaveProduct) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InsertProductMainContentPreview(
    @PreviewParameter(ProductPreviewProvider::class) product: Product
) {
    PiggyBankTheme {
        InsertProductContent(
            state = product,
            onEvent = {}
        )
    }
}
