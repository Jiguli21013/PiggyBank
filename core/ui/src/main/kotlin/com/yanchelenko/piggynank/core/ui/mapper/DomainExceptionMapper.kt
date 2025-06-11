package com.yanchelenko.piggynank.core.ui.mapper

import com.yanchelenko.piggybank.domain.exceptions.BaseDomainException
import com.yanchelenko.piggybank.domain.exceptions.NotFoundException
import com.yanchelenko.piggybank.domain.exceptions.ProductNotFoundException

fun BaseDomainException.toUserMessage(): String = when (this) {
    is ProductNotFoundException -> "Такой товар не найден" //todo to res
    is NotFoundException -> "Элемент не найден" //todo to res
    else -> message ?: "Неизвестная ошибка" //todo to res
}
