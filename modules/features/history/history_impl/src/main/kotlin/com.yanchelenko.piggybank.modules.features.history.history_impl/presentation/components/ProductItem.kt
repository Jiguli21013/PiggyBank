package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryEvent
import com.yanchelenko.piggybank.modules.base.ui_kit.components.InfoCard
import com.yanchelenko.piggybank.modules.base.ui_kit.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import com.yanchelenko.piggynank.core.ui.theme.AppShapes
import com.yanchelenko.piggynank.core.ui.theme.Dimens
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.features.history.history_impl.R

@Composable
internal fun ProductItem(
    modifier: Modifier = Modifier,
    product: ProductUiModel,
    onEvent: (HistoryEvent) -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        InfoCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Dimens.PaddingMedium,
                    vertical = Dimens.PaddingSmall
                )
                .clip(shape = AppShapes.large)
                .clickable { onEvent(HistoryEvent.OnProductClicked(product = product)) }
        ) {
            ProductField(label = stringResource(R.string.label_product_name), value = product.productName)
            ProductField(label = stringResource(R.string.label_product_id), value = product.productId.toString())
            ProductField(label = stringResource(R.string.label_barcode), value = product.barcode)
            ProductField(label = stringResource(R.string.label_price), value = "${product.price} ₽")
            ProductField(label = stringResource(R.string.label_weight), value = "${product.weight} г")
            ProductField(label = stringResource(R.string.label_price_per_kg), value = "${product.pricePerKg} ₽")
        }

        IconButton(
            onClick = { onEvent(HistoryEvent.OnProductDeleteClicked(product = product)) },
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(
                    x = Dimens.IconOffsetX,
                    y = Dimens.IconOffsetY
                )
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.content_description_delete),
                tint = Color.Red
            )
        }
    }
}

@Preview
@Composable
fun ProductItemPreview(
    @PreviewParameter(ProductPreviewProvider::class)
    product: ProductUiModel
) {
    PiggyBankTheme {
        ProductItem(
            product = product,
            onEvent = {}
        )
    }
}
