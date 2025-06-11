package com.yanchelenko.piggybank.domain.exceptions

sealed class BaseDomainException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)
