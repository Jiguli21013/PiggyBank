package com.yanchelenko.piggybank.domain.exceptions

open class NotFoundException(
    override val message: String
) : BaseDomainException(message)
