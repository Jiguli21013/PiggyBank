package com.yanchelenko.piggybank.modules.core.core_api.logger

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
    public fun e(
        tag: String,
        message: String
    )
}
