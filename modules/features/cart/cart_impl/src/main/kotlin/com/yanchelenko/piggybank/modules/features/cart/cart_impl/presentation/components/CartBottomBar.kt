package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yanchelenko.piggybank.modules.base.ui_kit.components.PrimaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingExtraSmall
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingSmall
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingSmall
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEvent
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartScreenState
import java.util.Locale

@Composable
fun CartBottomBar(
    modifier: Modifier = Modifier,
    state: CartScreenState,
    onEvent: (CartEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = PaddingMedium,
                vertical = PaddingSmall
            ),
        verticalArrangement = Arrangement.spacedBy(SpacingSmall)
    ) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PaddingSmall, bottom = PaddingExtraSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Итого за корзину:", //todo
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = String.format(Locale.getDefault(), "%.2f", state.totalPrice),//todo
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        PrimaryButton(
            text = if (state.isCartClosed) "Корзина закрыта" else "Закрыть корзину",//todo
            enabled = !state.isCartClosed,
            onClick = { onEvent(CartEvent.OnCloseCartClicked) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CartBottomBarPreview() {
    MaterialTheme {
        Column(verticalArrangement = Arrangement.spacedBy(space = SpacingSmall)) {
            CartBottomBar(
                state = CartScreenState(),
                onEvent = {},
                modifier = Modifier.fillMaxWidth()
            )
            CartBottomBar(
                state = CartScreenState(),
                onEvent = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
