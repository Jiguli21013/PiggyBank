package com.yanchelenko.piggybank.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
