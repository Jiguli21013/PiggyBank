package com.yanchelenko.piggybank.navigation.dispatcher

import kotlinx.coroutines.flow.SharedFlow

interface NavigationDispatcher {
    val navEvents: SharedFlow<NavEvent>
    suspend fun emit(event: NavEvent)
}
