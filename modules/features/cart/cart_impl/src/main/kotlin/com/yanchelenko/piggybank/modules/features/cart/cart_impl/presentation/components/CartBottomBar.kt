package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.components

import androidx.compose.ui.res.stringResource
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
import com.yanchelenko.piggybank.modules.base.ui_kit.components.buttons.PrimaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingExtraSmall
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingSmall
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingSmall
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEvent
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartScreenState
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.R

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
                .padding(
                    top = PaddingSmall,
                    bottom = PaddingExtraSmall
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.cart_total_label),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(R.string.cart_total_price_format, state.totalPrice),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        PrimaryButton(
            text = if (state.isCartClosed) stringResource(R.string.cart_closed_button) else stringResource(R.string.cart_close_button),
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
