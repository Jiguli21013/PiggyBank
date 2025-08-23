package com.yanchelenko.piggybank.result


inline fun <I : Any, O : Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error(data?.let(mapper), error)
        is RequestResult.InProgress -> RequestResult.InProgress(data?.let(mapper))
    }
}

fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> {
    return fold(
        onSuccess = { RequestResult.Success(it) },
        onFailure = { RequestResult.Error(error = it) }
    )
}

inline fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>): Result<R> =
    fold(
        onSuccess = { value -> transform(value) },
        onFailure = { error -> Result.failure(error) }
    )
