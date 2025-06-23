package com.yanchelenko.piggybank.common.ui_state

import com.yanchelneko.piggybank.common.core_utils.Logger

sealed interface CommonUiState<out T> {
    data object Initializing : CommonUiState<Nothing>
    data object Loading : CommonUiState<Nothing>
    data class Error<T>(val message: String? = null) : CommonUiState<T>
    data class Success<T>(val data: T) : CommonUiState<T>
}

inline fun <T> CommonUiState<T>.getDataOrLog(
    logTag: String,
    logger: Logger,
    block: (T) -> Unit
) {
    when (this) {
        is CommonUiState.Success -> block(data)
        else -> logger.e(logTag, "Event ignored: data not available in state = $this")
    }
}

inline fun <T> CommonUiState<T>.updateDataSuccess(
    logTag: String,
    logger: Logger,
    crossinline transform: T.() -> T
): CommonUiState<T> {
    return when (this) {
        is CommonUiState.Success -> {
            val newData = data.transform()
            if (newData == data) {
                logger.d(logTag, "Data unchanged, skipping state update")
                this
            } else {
                logger.d(logTag, "Data updated:\n• old = $data\n• new = $newData")
                CommonUiState.Success(newData)
            }
        }

        else -> {
            logger.e(logTag, "Can't update data: current state = $this")
            this
        }
    }
}
