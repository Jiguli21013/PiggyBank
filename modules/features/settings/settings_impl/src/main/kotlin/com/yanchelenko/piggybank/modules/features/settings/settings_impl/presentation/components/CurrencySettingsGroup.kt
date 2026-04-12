package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency

@Composable
fun CurrencySettingsGroup(
    selectedCurrency: AppCurrency,
    onCurrencySelected: (AppCurrency) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        AppCurrency.entries.forEach { currency ->
            CurrencyItem(
                currency = currency,
                isSelected = currency == selectedCurrency,
                onClick = { onCurrencySelected(currency) }
            )
        }
    }
}

@Composable
private fun CurrencyItem(
    currency: AppCurrency,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val text = if (isSelected) {
        "✓ ${currency.code} (${currency.symbol})"
    } else {
        "${currency.code} (${currency.symbol})"
    }

    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(
                horizontal = PaddingMedium,
                vertical = PaddingMedium
            )
    )
}
