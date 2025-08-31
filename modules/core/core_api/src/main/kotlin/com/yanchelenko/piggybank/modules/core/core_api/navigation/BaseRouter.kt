package com.yanchelenko.piggybank.modules.core.core_api.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseRouter(
    private val navigationDispatcher: NavigationDispatcher
) : CommonRouter {

    override fun navigateBack() {
        CoroutineScope(Dispatchers.Main).launch { //todo dispatchers
            navigationDispatcher.emit(event = NavEvent.NavigateBack)
        }
    }

    override fun navigateTo(destination: String) {
        CoroutineScope(Dispatchers.Main).launch { //todo dispatchers
            navigationDispatcher.emit(event = NavEvent.Navigate(destination))
        }
    }
}
