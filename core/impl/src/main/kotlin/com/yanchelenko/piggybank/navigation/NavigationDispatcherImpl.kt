package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcherImpl @Inject constructor() : NavigationDispatcher {
    private val _navEvents = MutableSharedFlow<NavEvent>()
    override val navEvents: SharedFlow<NavEvent> = _navEvents

    override suspend fun emit(event: NavEvent) = _navEvents.emit(value = event)
}
