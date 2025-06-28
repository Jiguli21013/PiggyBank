package com.yanchelenko.piggybank.domain.exceptions

// тоже лишняя абстракция, можно использовать уже имеющиеся исключения (например, IllegalStateException)
open class NotFoundException(
    override val message: String
) : BaseDomainException(message)
