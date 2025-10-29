package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.components

import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryOfScansEvent
import com.yanchelenko.piggybank.modules.base.ui_kit.components.InfoCard
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ProductField
import com.yanchelenko.piggybank.modules.base.ui_kit.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.modules.base.ui_kit.test.UiTestTags
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppShapes
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.base.resources.R as BaseR
import com.yanchelenko.piggybank.modules.features.history.history_of_scans_impl.R as HistoryR
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.lphas.CartBackground

@Composable
internal fun ScannedProductItem(
    modifier: Modifier = Modifier,
    product: ScannedProductUiModel,
    onEvent: (HistoryOfScansEvent) -> Unit
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .semantics { contentDescription = UiTestTags.HISTORY_OF_SCANS_ITEM_PREFIX + product.productId.toString() }
    ) {
        InfoCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Dimens.PaddingMedium,
                    vertical = Dimens.PaddingSmall
                )
                .clip(shape = AppShapes.large)
                .clickable { onEvent(HistoryOfScansEvent.OnProductClicked(product = product)) }
        ) {
            ProductField(label = stringResource(BaseR.string.label_product_name), value = product.productName)
            ProductField(label = stringResource(BaseR.string.label_product_id), value = product.productId.toString())
            ProductField(label = stringResource(BaseR.string.label_barcode), value = product.barcode)
            ProductField(label = stringResource(BaseR.string.label_price), value = "${product.price}")
            ProductField(label = stringResource(BaseR.string.label_weight), value = "${product.weight}")
            ProductField(label = stringResource(BaseR.string.label_price_per_kg), value = "${product.pricePerKg}")
        }

        IconButton(
            onClick = { onEvent(HistoryOfScansEvent.OnProductDeleteClicked(product = product)) },
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(
                    x = Dimens.IconOffsetX,
                    y = Dimens.IconOffsetY
                )
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(BaseR.string.content_description_delete),
                tint = Color.Red
            )
        }

        Icon(
            painter = painterResource(HistoryR.drawable.ic_scanner),
            contentDescription = stringResource(HistoryR.string.desc_scanner_icon),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = CartBackground),
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .aspectRatio(ratio = 3f)

        )

    }
}

@Preview
@Composable
fun ProductItemPreview(
    @PreviewParameter(ProductPreviewProvider::class)
    product: ScannedProductUiModel
) {
    PiggyBankTheme {
        ScannedProductItem(
            product = product,
            onEvent = {}
        )
    }
}
