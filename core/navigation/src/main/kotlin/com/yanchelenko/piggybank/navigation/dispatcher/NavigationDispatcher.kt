package com.yanchelenko.piggybank.navigation.dispatcher

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

interface NavigationDispatcher {
    val navEvents: SharedFlow<NavEvent>
    suspend fun emit(event: NavEvent)
}

@Singleton
class NavigationDispatcherImpl @Inject constructor() : NavigationDispatcher {
    private val _navEvents = MutableSharedFlow<NavEvent>()
    override val navEvents: SharedFlow<NavEvent> = _navEvents

    override suspend fun emit(event: NavEvent) {
        _navEvents.emit(event)
    }
}
