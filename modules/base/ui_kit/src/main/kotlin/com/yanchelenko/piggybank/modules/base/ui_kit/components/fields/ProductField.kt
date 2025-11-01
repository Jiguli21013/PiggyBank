package com.yanchelenko.piggybank.modules.base.ui_kit.components.fields

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingExtraSmall
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme

@Composable
fun ProductField(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(
        modifier = modifier
            .padding(vertical = PaddingExtraSmall)
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun PreviewProductField() {
    PiggyBankTheme {
        ProductField(label = "Название", value = "Хлеб")
    }
}
