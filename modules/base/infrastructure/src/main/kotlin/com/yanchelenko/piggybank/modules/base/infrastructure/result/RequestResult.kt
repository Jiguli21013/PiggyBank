package com.yanchelenko.piggybank.modules.base.infrastructure.result
//todo подумать над описанием
/**
 * RequestResult представляет собой запрос обновлениях данных,
 * который может происходить из нескольких источников
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
