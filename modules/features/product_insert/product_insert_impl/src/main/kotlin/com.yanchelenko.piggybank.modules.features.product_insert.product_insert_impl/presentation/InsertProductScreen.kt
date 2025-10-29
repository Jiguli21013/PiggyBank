package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.yanchelenko.piggybank.modules.dev_tools.RebuggerIfDebug
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductEffect
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductEvent
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.R
import com.yanchelenko.piggybank.modules.base.infrastructure.extensions.formatIfNonZero
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_kit.components.OutlinedInputField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.PrimaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.components.PriceInputField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ReadOnlyField
import com.yanchelenko.piggybank.modules.base.ui_kit.components.SecondaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.components.WeightInputField
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.base.ui_kit.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingExtraLarge
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingSmall
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductState
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.trackMap
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme

const val MIN_QUANTITY = 1
const val MAX_QUANTITY = 99

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
    state: InsertProductState,
    modifier: Modifier = Modifier,
    onEvent: (InsertProductEvent) -> Unit,
) {
    RebuggerIfDebug(trackMap = state.trackMap(), composableName = "InsertProductContent")

    val productNameLabel = stringResource(R.string.label_product_name)
    val priceLabel = stringResource(R.string.label_price_by_weight, state.scannedProduct.weight)
    val pricePerKgLabel = stringResource(R.string.label_price_per_kg)

    val backText = stringResource(R.string.action_back)
    val saveText = stringResource(R.string.action_save)

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(all = PaddingMedium),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(SpacingExtraLarge)) {

            OutlinedInputField(
                value = state.scannedProduct.productName,
                onValueChange = { onEvent(InsertProductEvent.ProductNameChanged(name = it)) },
                label = productNameLabel,
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            WeightInputField(
                weight = state.scannedProduct.weight,
                onWeightChange = { onEvent(InsertProductEvent.WeightChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            PriceInputField(
                value = state.priceInput,
                label = priceLabel,
                onTextChange = { onEvent(InsertProductEvent.PriceInputChanged(it)) },
                onPriceChange = { price -> onEvent(InsertProductEvent.PriceChanged(price)) },
                modifier = Modifier.fillMaxWidth()
            )

            ReadOnlyField(
                label = pricePerKgLabel,
                value = state.scannedProduct.pricePerKg.formatIfNonZero()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        CartControls(
            quantity = state.quantity,
            isInCart = state.isInCart,
            onEvent = onEvent,
            modifier = modifier.padding(bottom = SpacingSmall)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = SpacingSmall),
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
    @PreviewParameter(ProductPreviewProvider::class)
    product: ScannedProductUiModel
) {
    PiggyBankTheme {
        InsertProductContent(
            state =  InsertProductState(),
            onEvent = {}
        )
    }
}
