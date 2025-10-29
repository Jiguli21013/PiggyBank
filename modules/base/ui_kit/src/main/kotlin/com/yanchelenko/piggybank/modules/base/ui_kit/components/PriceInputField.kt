package com.yanchelenko.piggybank.modules.base.ui_kit.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

/**
 * A reusable price input field that:
 * - Allows only digits and one decimal separator (comma or dot).
 * - Keeps the first dot and removes extra ones.
 * - Emits both the raw text (for UI) and numeric value (for logic).
 */
@Composable
fun PriceInputField(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    onPriceChange: (Double) -> Unit,
) {
    OutlinedInputField(
        value = value,
        onValueChange = { input ->
            // Normalize decimal separator and filter allowed chars
            val sanitized = input.replace(',', '.')
                .filter { it.isDigit() || it == '.' }

            // Keep only the first dot and allow up to two digits after it
            val normalized = buildString {
                var dotSeen = false
                var digitsAfterDot = 0
                for (ch in sanitized) {
                    if (ch == '.') {
                        if (!dotSeen) {
                            append(ch)
                            dotSeen = true
                        }
                    } else {
                        if (!dotSeen) {
                            append(ch)
                        } else if (digitsAfterDot < 2) {
                            append(ch)
                            digitsAfterDot++
                        }
                    }
                }
            }

            onTextChange(normalized)

            val numeric = normalized.toDoubleOrNull() ?: 0.0
            onPriceChange(numeric)
        },
        label = label,
        keyboardType = KeyboardType.Decimal,
        modifier = modifier
    )
}
