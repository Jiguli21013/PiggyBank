package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.yanchelenko.piggybank.modules.base.ui_kit.components.InfoCard
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsState

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
    InfoCard(modifier = modifier.fillMaxWidth()) {
        Text(
            text = changeHistoryTitle,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

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

fun Int.toSignedGramsText(unitLabel: String): String {
    val sign = if (this > 0) "+" else ""
    return "$sign$this $unitLabel"
}

fun Double.toSignedNumberText(): String {
    val sign = if (this > 0) "+" else ""
    return "$sign$this"
}
