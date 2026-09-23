package com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher

sealed class NavEvent {
    /** Opens a screen while keeping the previous screen available for Back. */
    data class Navigate(val route: String) : NavEvent()
    /** Opens a main section and removes the previous section's screen stack. */
    data class NavigateRoot(val route: String) : NavEvent()
    data object NavigateBack : NavEvent()
}
