package com.yanchelenko.piggybank.modules.core.core_api.domain.mapper

import com.yanchelenko.piggybank.modules.core.core_api.exceptions.BaseDomainException
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.NotFoundException
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.ProductNotFoundException

fun BaseDomainException.toUserMessage(): String = when (this) {
    is ProductNotFoundException -> "Такой товар не найден" //todo to res
    is NotFoundException -> "Элемент не найден" //todo to res
    else -> message ?: "Неизвестная ошибка" //todo to res
}
