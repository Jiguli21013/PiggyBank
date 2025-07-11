package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.yanchelenko.piggybank.modules.base.infrastructure.extensions.formatAsHeader
import com.yanchelenko.piggynank.core.ui.theme.AppShapes
import com.yanchelenko.piggynank.core.ui.theme.CustomColors.SurfaceVariantSemiTransparent
import com.yanchelenko.piggynank.core.ui.theme.Dimens.HeaderHeight
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingSmall
import com.yanchelenko.piggynank.core.ui.theme.Dimens.ElevationSmall
import kotlinx.datetime.LocalDate

@Composable
fun DateHeader(
    modifier: Modifier,
    date: LocalDate,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = SurfaceVariantSemiTransparent,
            shape = AppShapes.medium,
            tonalElevation = ElevationSmall,
            modifier = modifier
                .padding(
                    horizontal = PaddingMedium,
                    vertical = PaddingSmall
                )
                .height(
                    height = HeaderHeight
                )
        ) {
            Text(
                text = remember(date) { date.formatAsHeader() },
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .padding(horizontal = PaddingMedium),
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewDateHeader() {
    DateHeader(
        date = LocalDate(
            year = 2025,
            monthNumber = 6,
            dayOfMonth = 6
        ),
        modifier = Modifier
    )
}
