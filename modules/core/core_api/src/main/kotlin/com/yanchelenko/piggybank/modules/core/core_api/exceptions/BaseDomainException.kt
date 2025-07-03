package com.yanchelenko.piggybank.modules.core.core_api.exceptions

sealed class BaseDomainException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)
