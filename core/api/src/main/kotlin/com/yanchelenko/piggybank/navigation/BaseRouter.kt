package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.navigation.dispatcher.NavEvent
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseRouter( // не понял, чем отличается от CommonRouter
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
