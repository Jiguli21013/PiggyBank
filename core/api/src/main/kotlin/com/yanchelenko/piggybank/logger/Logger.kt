package com.yanchelenko.piggybank.logger

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
