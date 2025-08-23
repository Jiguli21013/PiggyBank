package com.yanchelenko.piggybank.exceptions

open class NotFoundException(
    override val message: String
) : Exception(message)
