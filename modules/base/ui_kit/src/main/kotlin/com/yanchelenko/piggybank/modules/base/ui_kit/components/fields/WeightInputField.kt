package com.yanchelenko.piggybank.modules.base.ui_kit.components.fields

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.yanchelenko.piggybank.modules.base.ui_kit.R
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium

const val MAX_WEIGHT_GRAMS = 10_000

@Composable
fun WeightInputField(
    weight: Int,
    onWeightChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val weightLabel = stringResource(R.string.label_weight_grams)

    OutlinedTextField(
        value = weight.takeIf { it > 0 }?.toString() ?: "",
        onValueChange = { raw ->
            val digitsOnly = raw.filter { it.isDigit() }
            val candidate = digitsOnly.toIntOrNull()
            when {
                raw.isEmpty() -> onWeightChange(0)
                candidate == null -> Unit
                candidate <= MAX_WEIGHT_GRAMS -> onWeightChange(candidate)
                else -> Unit
            }
        },
        label = { Text(text = weightLabel) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = MaterialTheme.typography.bodyLarge,
        trailingIcon = {
            Text(
                text = stringResource(R.string.label_weight_kg_format, weight / 1000.0),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.padding(end = PaddingMedium)
            )
        },
        modifier = modifier
    )
}
