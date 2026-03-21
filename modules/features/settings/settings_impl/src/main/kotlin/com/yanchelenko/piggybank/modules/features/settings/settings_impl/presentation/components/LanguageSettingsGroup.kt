package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingSmall
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.SpacingSmall
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toDisplayName
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage

@Composable
fun LanguageSettingsGroup(
    selectedLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = SpacingSmall),
        modifier = Modifier.padding(horizontal = PaddingSmall),
    ) {
        SettingsOptionRow(
            title = AppLanguage.SYSTEM.toDisplayName(),
            selected = selectedLanguage == AppLanguage.SYSTEM,
            onClick = { onLanguageSelected(AppLanguage.SYSTEM) },
        )
        SettingsOptionRow(
            title = AppLanguage.ENGLISH.toDisplayName(),
            selected = selectedLanguage == AppLanguage.ENGLISH,
            onClick = { onLanguageSelected(AppLanguage.ENGLISH) },
        )
        SettingsOptionRow(
            title = AppLanguage.RUSSIAN.toDisplayName(),
            selected = selectedLanguage == AppLanguage.RUSSIAN,
            onClick = { onLanguageSelected(AppLanguage.RUSSIAN) },
        )
    }
}
