package com.yanchelenko.piggybank.modules.base.infrastructure.mvi

sealed interface CommonUiState<out T> {
    data object Initializing : CommonUiState<Nothing>
    data object Loading : CommonUiState<Nothing>

    data class Error<T>(val message: String? = null) : CommonUiState<T>
    data class Success<T>(val data: T) : CommonUiState<T>
}

inline fun <T> CommonUiState<T>.getData(
    block: (T) -> Unit
) {
    when (this) {
        is CommonUiState.Success -> block(data)
        else -> {}
    }
}

inline fun <T> CommonUiState<T>.updateDataSuccess(
    crossinline transform: T.() -> T
): CommonUiState<T> {
    return when (this) {
        is CommonUiState.Success -> {
            val newData = data.transform()
            if (newData == data) {
                this
            } else {
                CommonUiState.Success(newData)
            }
        }

        else -> {
            this
        }
    }
}
