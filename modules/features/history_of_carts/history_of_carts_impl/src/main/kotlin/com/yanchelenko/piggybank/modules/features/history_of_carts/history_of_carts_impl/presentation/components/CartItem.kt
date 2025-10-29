package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.yanchelenko.piggybank.modules.base.ui_kit.components.InfoCard
import com.yanchelenko.piggybank.modules.base.ui_kit.components.ProductField
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppShapes
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.lphas.CartBackground
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.R
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.CartUiModel
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.preview.CartPreviewProvider
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.state.HistoryOfCartsEvent

@Composable
internal fun CartItem(
    modifier: Modifier = Modifier,
    cart: CartUiModel,
    onEvent: (HistoryOfCartsEvent) -> Unit
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
                .clickable {
                    //onEvent(HistoryOfCartsEvent.OnCartClicked(cart = cart))
                    onEvent(HistoryOfCartsEvent.OnCartClicked)
                }
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                )
                {
                    ProductField(
                        label = stringResource(R.string.label_store_name),
                        value = cart.storeName ?: stringResource(R.string.value_store_unknown)
                    )
                    ProductField(
                        label = stringResource(R.string.label_total_items),
                        value = cart.totalItems.toString()
                    )
                    ProductField(
                        label = stringResource(R.string.label_closed_at),
                        value = cart.closedAtText
                    )
                    ProductField(
                        label = stringResource(R.string.label_price_per_cart),
                        value = cart.totalPrice.toString()
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_cart),
                    contentDescription = stringResource(R.string.desc_cart_icon),
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = CartBackground),
                    modifier = Modifier
                        .aspectRatio(ratio = 3f)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview(
    @PreviewParameter(CartPreviewProvider::class)
    cart: CartUiModel
) {
    PiggyBankTheme {
        CartItem(
            cart = cart,
            onEvent = {}
        )
    }
}
