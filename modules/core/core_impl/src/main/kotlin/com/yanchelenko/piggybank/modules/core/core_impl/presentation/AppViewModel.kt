package com.yanchelenko.piggybank.modules.core.core_impl.presentation

import androidx.lifecycle.ViewModel
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val navDispatcher: NavigationDispatcher
) : ViewModel()

