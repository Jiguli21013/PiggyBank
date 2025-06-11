package com.yanchelneko.piggybank.common.core_utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

@JvmInline
value class AppDispatchers(
    val provider: Provider
) {
    interface Provider {
        val default: CoroutineDispatcher
        val io: CoroutineDispatcher
        val main: CoroutineDispatcher
        val unconfined: CoroutineDispatcher
    }
}
