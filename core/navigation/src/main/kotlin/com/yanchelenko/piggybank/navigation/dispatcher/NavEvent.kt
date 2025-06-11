package com.yanchelenko.piggybank.navigation.dispatcher

sealed class NavEvent {
    data class Navigate(val route: String) : NavEvent()
    data class NavigateAndPopUp(val route: String, val popUp: String) : NavEvent()
    data class NavigateRoot(val route: String) : NavEvent()
    data object NavigateBack : NavEvent()
}
