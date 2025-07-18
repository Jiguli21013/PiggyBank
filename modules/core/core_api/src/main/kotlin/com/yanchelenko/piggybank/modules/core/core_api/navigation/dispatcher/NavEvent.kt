package com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher

sealed class NavEvent {
    data class Navigate(val route: String) : NavEvent()
    data class NavigateRoot(val route: String) : NavEvent()
    data object NavigateBack : NavEvent()
}
