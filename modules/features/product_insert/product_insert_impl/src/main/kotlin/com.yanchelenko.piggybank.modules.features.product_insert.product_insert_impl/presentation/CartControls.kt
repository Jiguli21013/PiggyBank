package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.yanchelenko.piggybank.modules.base.ui_kit.components.buttons.SecondaryButton
import com.yanchelenko.piggybank.modules.base.ui_kit.components.buttons.ThirdButton
import com.yanchelenko.piggybank.modules.base.ui_kit.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppShapes
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.CustomColors
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.CustomColors.SurfaceVariantSemiTransparent
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingSmall
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.RoundButtonLarge
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingLarge
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.TextSizes
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.R
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductEvent

/**
 * Displays quantity controls and Add/Remove from cart button.
 *
 * - Buttons `+` and `–` allow changing the quantity within [MIN_QUANTITY, MAX_QUANTITY].
 * - The main action button toggles between "Add to cart" and "Remove from cart"
 *   depending on [state.isInCart].
 */
@Composable
fun CartControls(
    quantity: Int,
    isInCart: Boolean,
    onEvent: (InsertProductEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val addToCartText = stringResource(R.string.add_to_cart)
    val removeFromCartText = stringResource(R.string.remove_from_cart)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SurfaceVariantSemiTransparent,
                shape = AppShapes.large
            )
            .padding(
                horizontal = PaddingMedium,
                vertical = PaddingSmall
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SpacingMedium),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpacingMedium),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SecondaryButton(
                    onClick = { onEvent(InsertProductEvent.DecreaseQuantity) },
                    enabled = quantity > MIN_QUANTITY && !isInCart,
                    text = "-",
                    modifier = Modifier.size(RoundButtonLarge)
                )

                Text(
                    text = quantity.toString(),
                    fontSize = TextSizes.Quantity,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                SecondaryButton(
                    onClick = { onEvent(InsertProductEvent.IncreaseQuantity) },
                    enabled = quantity < MAX_QUANTITY && !isInCart,
                    text = "+",
                    modifier = Modifier.size(RoundButtonLarge)
                )
            }

            ThirdButton(
                text = if (isInCart) removeFromCartText else addToCartText,
                onClick = {
                    if (isInCart) {
                        onEvent(InsertProductEvent.RemoveFromCart)
                    } else {
                        onEvent(InsertProductEvent.AddToCart)
                    }
                },
                color = if (isInCart) {
                    CustomColors.RemoveFromCart
                } else {
                    CustomColors.AddToCart
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartControlsPreview(
    @PreviewParameter(ProductPreviewProvider::class)
    product: ScannedProductUiModel
) {
    MaterialTheme {
        Column(verticalArrangement = Arrangement.spacedBy(space = SpacingLarge)) {
            CartControls(
                quantity = 18,
                isInCart = false,
                onEvent = {}
            )
            CartControls(
                quantity = 16,
                isInCart = true,
                onEvent = {}
            )
        }
    }
}
