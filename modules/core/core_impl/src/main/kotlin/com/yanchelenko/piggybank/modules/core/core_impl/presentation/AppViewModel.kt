package com.yanchelenko.piggybank.modules.core.core_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetActiveCartTotalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val navDispatcher: NavigationDispatcher,
    getActiveCartTotals: GetActiveCartTotalsUseCase
) : ViewModel() {

    /**
     * Hot StateFlow with the number of items in the ACTIVE cart.
     * Used for the cart badge in the BottomNavigation across the whole app.
     */
    val cartItemsCount: StateFlow<Int> = getActiveCartTotals()
        .map { it.itemsCount }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = 0
        )
}
