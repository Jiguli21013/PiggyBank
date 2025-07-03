package com.yanchelenko.piggybank.modules.core.core_api.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseRouter(
    private val dispatcher: NavigationDispatcher
) : CommonRouter {

    override fun navigateBack() {
        CoroutineScope(Dispatchers.Main).launch { //todo dispatchers
            dispatcher.emit(event = NavEvent.NavigateBack)
        }
    }

    fun navigateTo(destination: String) {
        CoroutineScope(Dispatchers.Main).launch { //todo dispatchers
            dispatcher.emit(event = NavEvent.Navigate(destination))
        }
    }
}
