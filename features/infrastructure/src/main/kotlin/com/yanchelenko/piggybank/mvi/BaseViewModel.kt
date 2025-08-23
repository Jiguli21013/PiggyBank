package com.yanchelenko.piggybank.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<EVENT, STATE, EFFECT>(
    initialState: STATE,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    protected val state: STATE
        get() = _uiState.value

    protected fun setState(reducer: STATE.() -> STATE) {
        val current = _uiState.value
        val newState = current.reducer()

        _uiState.value = newState
    }

    private val _effect = Channel<EFFECT>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    protected fun sendEffect(builder: () -> EFFECT) {
        viewModelScope.launch {
            _effect.send(builder())
        }
    }

    abstract fun onEvent(event: EVENT)

    private companion object {
        private const val LOG_TAG = "BaseViewModel"
    }

}
