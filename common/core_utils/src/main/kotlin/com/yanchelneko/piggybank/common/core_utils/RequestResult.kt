package com.yanchelneko.piggybank.common.core_utils
//todo подумать над описанием // я увидел в RequestResultExt.kt что используется маппинг из Result котлиновского,
// из этого вопрос, зачем нам этот класс, почему не использовать котлиновский?
/**
 * RequestResult представляет собой запрос обновлениях данных,
 * который может происходить из нескольких источников
 */
public sealed class RequestResult<out E : Any>(public open val data: E? = null) {
    public class InProgress<E : Any>( // загрузка вроде UI специфичная штука, не уверен что тут нужна
        data: E? = null,
    ) : RequestResult<E>(data)

    public class Success<E : Any>(
        override val data: E,
    ) : RequestResult<E>(data)

    public class Error<E : Any>(
        data: E? = null, // а тут не будет null каждый раз?
        public val error: Throwable? = null,
    ) : RequestResult<E>(data)
}
