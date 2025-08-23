package com.yanchelenko.piggybank.mvi

// нет смысла переусложнять название
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Error<T>(
        val throwable: Throwable? = null,
        val message: String? = null,
    ) : UiState<T>

    data class Success<T>(val data: T) : UiState<T>
}

inline fun <T> UiState<T>.onSuccess(block: (T) -> Unit) { // переменовал, потому что ничего не получаешь
    if (this is UiState.Success) block(data)
}

inline fun <T> UiState<T>.updateDataSuccess(crossinline transform: T.() -> T): UiState<T> =
    when (this) {
        is UiState.Success -> {
            val newData = data.transform()
            if (newData == data) {
                this
            } else {
                UiState.Success(newData)
            }
        }

        else -> {
            this
        }
    }

