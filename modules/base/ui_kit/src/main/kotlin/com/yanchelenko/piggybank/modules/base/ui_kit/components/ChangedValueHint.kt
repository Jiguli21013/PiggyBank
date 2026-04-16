package com.yanchelenko.piggybank.modules.base.ui_kit.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChangedValueHint(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
    )
}
