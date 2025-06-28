package com.yanchelneko.piggybank.common.core_utils

public interface Logger {
    /**
    Обычное логирование
     */
    public fun d(
        tag: String,
        message: String
    )
    /**
     Ошибки, исключения, сбои
     */
    public fun e( // нужно добавить ошибку в качестве параметра с типом Throwable?
        tag: String,
        message: String
    )
}
