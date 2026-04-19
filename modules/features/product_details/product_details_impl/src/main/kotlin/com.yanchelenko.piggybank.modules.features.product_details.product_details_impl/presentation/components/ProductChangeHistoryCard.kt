package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yanchelenko.piggybank.modules.base.ui_kit.components.InfoCard
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingMedium

@Composable
fun ProductChangeHistoryCard(
    state: ProductDetailsState,
    changeHistoryTitle: String,
    previousPriceLabel: String,
    currentPriceLabel: String,
    priceDifferenceLabel: String,
    previousWeightLabel: String,
    currentWeightLabel: String,
    weightDifferenceLabel: String,
    previousWeightValue: String?,
    weightValue: String,
    weightDeltaValue: String?,
    modifier: Modifier = Modifier,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val arrowRotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = spring(),
        label = "arrow_rotation",
    )

    InfoCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = changeHistoryTitle,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.rotate(arrowRotation),
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(animationSpec = spring()),
            exit = fadeOut() + shrinkVertically(animationSpec = spring()),
        ) {
            Column {
                Spacer(modifier = Modifier.height(SpacingMedium))

                if (state.hasPriceChanged) {
                    InfoRow(
                        label = previousPriceLabel,
                        value = state.previousPrice?.let { "$it" } ?: "—"
                    )
                    InfoRow(
                        label = currentPriceLabel,
                        value = state.product.formattedPrice
                    )
                    InfoRow(
                        label = priceDifferenceLabel,
                        value = state.priceDelta?.toSignedNumberText() ?: "—"
                    )
                }

                if (state.hasPriceChanged && state.hasWeightChanged) {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (state.hasWeightChanged) {
                    InfoRow(
                        label = previousWeightLabel,
                        value = previousWeightValue ?: "—"
                    )
                    InfoRow(
                        label = currentWeightLabel,
                        value = weightValue
                    )
                    InfoRow(
                        label = weightDifferenceLabel,
                        value = weightDeltaValue ?: "—"
                    )
                }
            }
        }
    }
}

fun Int.toSignedGramsText(unitLabel: String): String {
    val sign = if (this > 0) "+" else ""
    return "$sign$this $unitLabel"
}

fun Double.toSignedNumberText(): String {
    val sign = if (this > 0) "+" else ""
    return "$sign$this"
}
