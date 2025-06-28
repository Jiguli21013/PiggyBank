package com.yanchelenko.piggybank.navigation.dispatcher

sealed class NavEvent {
    data class Navigate(val route: String) : NavEvent()
    data class NavigateAndPopUp(val route: String, val popUp: String) : NavEvent()
    // верхние два можно объединить наверное, popUp сделать опциональным
    data class NavigateRoot(val route: String) : NavEvent()
    data object NavigateBack : NavEvent()
}
