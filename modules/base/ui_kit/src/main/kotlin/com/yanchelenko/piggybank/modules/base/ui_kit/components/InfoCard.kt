package com.yanchelenko.piggybank.modules.base.ui_kit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppShapes
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.CustomColors.PrimaryTransparent10
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(PaddingMedium),
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = PrimaryTransparent10,
        shape = AppShapes.large
    ) { Column(modifier = Modifier.padding(paddingValues = padding)) {
        content()
    } }
}
