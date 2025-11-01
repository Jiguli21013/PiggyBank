package com.yanchelenko.piggybank.modules.base.infrastructure.result

/**
 * Represents the result of a data request or update operation.
 * It can originate from multiple sources (e.g., network, cache, database).
 */
public sealed class RequestResult<out E : Any>(public open val data: E? = null) {
    public class InProgress<E : Any>(
        data: E? = null,
    ) : RequestResult<E>(data)

    public class Success<E : Any>(
        override val data: E,
    ) : RequestResult<E>(data)

    public class Error<E : Any>(
        data: E? = null,
        public val error: Throwable? = null,
    ) : RequestResult<E>(data)
}
