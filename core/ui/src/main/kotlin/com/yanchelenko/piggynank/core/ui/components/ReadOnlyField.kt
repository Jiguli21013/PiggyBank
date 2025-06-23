package com.yanchelenko.piggynank.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.yanchelenko.piggynank.core.ui.theme.AppShapes
import com.yanchelenko.piggynank.core.ui.theme.CustomColors.fsad
import com.yanchelenko.piggynank.core.ui.theme.Dimens.ElevationSmall
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingSmall

@Composable
fun ReadOnlyField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.error
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Surface(
            shape = AppShapes.medium,
            tonalElevation = ElevationSmall,
            color = fsad,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PaddingSmall)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = color,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = PaddingMedium)
            )
        }
    }
}
