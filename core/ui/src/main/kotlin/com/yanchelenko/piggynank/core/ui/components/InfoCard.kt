package com.yanchelenko.piggynank.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yanchelenko.piggynank.core.ui.theme.AppShapes
import com.yanchelenko.piggynank.core.ui.theme.CustomColors.PrimaryTransparent10
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(PaddingMedium),
    content: @Composable () -> Unit // этот первый в списке
) {
    Surface(
        modifier = modifier,
        color = PrimaryTransparent10,
        shape = AppShapes.large
    ) {
        Column(modifier = Modifier.padding(padding)) { content() }
    }
}
