package com.yanchelenko.piggybank.modules.base.infrastructure.result


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

/**
 * Combines two [RequestResult] values into one.
 * - Returns [RequestResult.InProgress] if any of them is InProgress.
 * - Propagates [RequestResult.Error] if any fails.
 * - Returns [RequestResult.Success] with combined data if both succeed.
 */
inline fun <A : Any, B : Any, R : Any> combineResults(
    first: RequestResult<A>,
    second: RequestResult<B>,
    crossinline transform: (A?, B?) -> R
): RequestResult<R> {
    val aData: A? = when (first) {
        is RequestResult.Success -> first.data
        is RequestResult.InProgress -> first.data
        else -> null
    }
    val bData: B? = when (second) {
        is RequestResult.Success -> second.data
        is RequestResult.InProgress -> second.data
        else -> null
    }

    return when {
        first is RequestResult.Error -> RequestResult.Error<R>(error = first.error)
        second is RequestResult.Error -> RequestResult.Error<R>(error = second.error)

        first is RequestResult.Success && second is RequestResult.Success ->
            RequestResult.Success(transform(first.data, second.data))

        else -> {
            val combined: R? = try {
                transform(aData, bData)
            } catch (_: Throwable) {
                null
            }
            RequestResult.InProgress<R>(combined)
        }
    }
}
