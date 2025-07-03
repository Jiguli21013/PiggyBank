package com.yanchelenko.piggybank.modules.core.core_api.exceptions

open class NotFoundException(
    override val message: String
) : BaseDomainException(message)
