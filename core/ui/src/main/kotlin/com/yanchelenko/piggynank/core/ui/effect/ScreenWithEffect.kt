package com.yanchelenko.piggynank.core.ui.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <STATE, EVENT, EFFECT> ScreenWithEffect(
    modifier: Modifier = Modifier,
    state: STATE,
    effectFlow: Flow<EFFECT>,
    onEvent: (EVENT) -> Unit,
    onEffect: (EFFECT) -> Unit,
    content: @Composable (state: STATE, onEvent: (EVENT) -> Unit, modifier: Modifier) -> Unit
) {
    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect -> onEffect(effect) }
    }

    content(state, onEvent, modifier)
}
