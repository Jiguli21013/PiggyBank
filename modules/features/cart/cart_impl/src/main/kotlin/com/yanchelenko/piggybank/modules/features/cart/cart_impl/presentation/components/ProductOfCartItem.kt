package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.components

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
import com.yanchelenko.piggybank.modules.base.ui_kit.components.InfoCard
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ProductField
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEvent
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppShapes
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.base.resources.R as BaseR
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.R
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.preview.ProductOfCartPreviewProvider

@Composable
internal fun ProductOfCartItem(
    modifier: Modifier = Modifier,
    productOfCart: ProductOfCartUiModel,
    onEvent: (CartEvent) -> Unit
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
                .clickable { onEvent(CartEvent.OnProductOfCartClicked(productOfCart = productOfCart)) }
        ) {
            ProductField(label = stringResource(BaseR.string.label_product_id), value = productOfCart.productId.toString())
            ProductField(label = stringResource(BaseR.string.label_product_name), value = productOfCart.name)
            ProductField(label = stringResource(BaseR.string.label_barcode), value = productOfCart.barcode)
            ProductField(label = stringResource(BaseR.string.label_weight), value = "${productOfCart.weightText}")

            ProductField(
                label = stringResource(R.string.label_price_per_current_weight),
                value = "${productOfCart.unitPrice} for ${productOfCart.weightText} gramms" //todo mapper
            )
            ProductField(label = stringResource(R.string.label_price_per_kg), value = "${productOfCart.formattedPricePerKg}")
            ProductField(
                label = stringResource(R.string.label_total_price),
                value = "${productOfCart.unitPrice} × ${productOfCart.quantityText} = ${productOfCart.totalPriceText}"
            )
        }

        IconButton(
            onClick = { onEvent(CartEvent.OnProductOfCartDeleteClicked(productOfCart = productOfCart)) },
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
    }
}

@Preview
@Composable
fun ProductItemPreview(
    @PreviewParameter(ProductOfCartPreviewProvider::class)
    productOfCart: ProductOfCartUiModel
) {
    PiggyBankTheme {
        ProductOfCartItem(
            productOfCart = productOfCart,
            onEvent = {}
        )
    }
}
