package com.yanchelenko.piggybank.presentation

import androidx.lifecycle.ViewModel
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val navDispatcher: NavigationDispatcher
) : ViewModel()

