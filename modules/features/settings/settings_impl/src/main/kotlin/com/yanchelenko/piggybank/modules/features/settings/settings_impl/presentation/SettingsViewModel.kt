package com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation

import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.ObserveAppLanguageUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.ObserveAppThemeUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.UpdateAppLanguageUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.UpdateAppThemeUseCase
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state.SettingsEffect
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state.SettingsEvent
import com.yanchelenko.piggybank.modules.features.settings.settings_impl.presentation.state.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.modules.core.core_api.domain.ObserveCurrencyUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.UpdateCurrencyUseCase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val observeAppThemeUseCase: ObserveAppThemeUseCase,
    private val observeAppLanguageUseCase: ObserveAppLanguageUseCase,
    private val updateAppThemeUseCase: UpdateAppThemeUseCase,
    private val updateAppLanguageUseCase: UpdateAppLanguageUseCase,
    private val observeCurrencyUseCase: ObserveCurrencyUseCase,
    private val updateCurrencyUseCase: UpdateCurrencyUseCase,
    private val logger: Logger,
) : BaseViewModel<SettingsEvent, CommonUiState<SettingsState>, SettingsEffect>(
    initialState = CommonUiState.Initializing
) {
    init { observeSettings() }

    private fun observeSettings() {
        logger.d(LOG_TAG, "observeSettings()")
        viewModelScope.launch {
            combine(
                flow = observeAppThemeUseCase(),
                flow2 = observeAppLanguageUseCase(),
                flow3 = observeCurrencyUseCase(),
            ) { theme, language, currency ->
                SettingsState(
                    selectedTheme = theme,
                    selectedLanguage = language,
                    selectedCurrency = currency,
                )
            }.collectLatest { settingsState ->
                logger.d(LOG_TAG,"settingsState -> $settingsState")
                setState { CommonUiState.Success(data = settingsState) }
            }
        }
    }

    private fun onThemeSelected(event: SettingsEvent.OnThemeSelected) {
        logger.d(LOG_TAG, "onThemeSelected(theme=${event.theme})")
        viewModelScope.launch {
            updateAppThemeUseCase(event.theme)
        }
    }

    private fun onLanguageSelected(event: SettingsEvent.OnLanguageSelected) {
        logger.d(LOG_TAG, "onLanguageSelected(language=${event.language})")
        viewModelScope.launch {
            updateAppLanguageUseCase(event.language)
        }
    }

    private fun onCurrencySelected(event: SettingsEvent.OnCurrencySelected) {
        logger.d(LOG_TAG, "onCurrencySelected(currency=${event.currency})")
        viewModelScope.launch {
            updateCurrencyUseCase(event.currency)
        }
    }

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnThemeSelected -> onThemeSelected(event)
            is SettingsEvent.OnLanguageSelected -> onLanguageSelected(event)
            is SettingsEvent.OnCurrencySelected -> onCurrencySelected(event)
        }
    }

    private companion object {
        const val LOG_TAG = "SettingsVM"
    }
}
