package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.ui_kit.components.CenteredLoader
import com.yanchelenko.piggybank.modules.base.ui_kit.mvi.ScreenWithEffect
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingLarge
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.PiggyBankTheme
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.R
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.components.SettingsExpandableButton
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.components.ThemeSettingsGroup
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.components.LanguageSettingsGroup
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state.SettingsEffect
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state.SettingsEvent
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state.SettingsState

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: SettingsViewModel = hiltViewModel()

    SettingsScreen(
        viewModel = viewModel,
        modifier = modifier,
    )
}

@Composable
internal fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel,
) {
    val state by viewModel.uiState.collectAsState()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

    ScreenWithEffect(
        state = state,
        effectFlow = effectFlow,
        onEvent = viewModel::onEvent,
        modifier = modifier,
        onEffect = { effect ->
            when (effect) {
                is SettingsEffect.ShowMessage -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        },
        content = { uiState, sendEvent, innerModifier ->
            when (uiState) {
                is CommonUiState.Success -> {
                    SettingsContent(
                        state = uiState.data,
                        modifier = innerModifier,
                        onEvent = sendEvent,
                    )
                }

                CommonUiState.Initializing -> {
                    CenteredLoader(modifier = innerModifier)
                }

                else -> CenteredLoader(modifier = innerModifier)
            }
        },
    )
}

@Composable
private fun SettingsContent(
    state: SettingsState,
    modifier: Modifier = Modifier,
    onEvent: (SettingsEvent) -> Unit,
) {
    var isThemeExpanded by rememberSaveable { mutableStateOf(false) }
    var isLanguageExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = PaddingMedium),
        verticalArrangement = Arrangement.spacedBy(PaddingLarge),
    ) {
        SettingsExpandableButton(
            title = stringResource(R.string.settings_theme_title),
            isExpanded = isThemeExpanded,
            onClick = { isThemeExpanded = !isThemeExpanded },
        )

        AnimatedVisibility(visible = isThemeExpanded) {
            ThemeSettingsGroup(
                selectedTheme = state.selectedTheme,
                onThemeSelected = { theme ->
                    onEvent(SettingsEvent.OnThemeSelected(theme))
                },
            )
        }

        SettingsExpandableButton(
            title = stringResource(R.string.settings_language_title),
            isExpanded = isLanguageExpanded,
            onClick = { isLanguageExpanded = !isLanguageExpanded },
        )

        AnimatedVisibility(visible = isLanguageExpanded) {
            LanguageSettingsGroup(
                selectedLanguage = state.selectedLanguage,
                onLanguageSelected = { language ->
                    onEvent(SettingsEvent.OnLanguageSelected(language))
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsContentPreview() {
    PiggyBankTheme {
        SettingsContent(
            state = SettingsState(
                selectedTheme = AppTheme.DARK,
                selectedLanguage = AppLanguage.ENGLISH,
            ),
            onEvent = {},
        )
    }
}
