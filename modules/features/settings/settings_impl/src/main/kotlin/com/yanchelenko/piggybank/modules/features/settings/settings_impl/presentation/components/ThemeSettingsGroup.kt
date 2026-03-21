package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingSmall
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingSmall
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.R

@Composable
fun ThemeSettingsGroup(
    selectedTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SpacingSmall),
        modifier = Modifier.padding(horizontal = PaddingSmall),
    ) {
        SettingsOptionRow(
            title = stringResource(R.string.settings_theme_system),
            selected = selectedTheme == AppTheme.SYSTEM,
            onClick = { onThemeSelected(AppTheme.SYSTEM) },
        )
        SettingsOptionRow(
            title = stringResource(R.string.settings_theme_light),
            selected = selectedTheme == AppTheme.LIGHT,
            onClick = { onThemeSelected(AppTheme.LIGHT) },
        )
        SettingsOptionRow(
            title = stringResource(R.string.settings_theme_dark),
            selected = selectedTheme == AppTheme.DARK,
            onClick = { onThemeSelected(AppTheme.DARK) },
        )
    }
}
