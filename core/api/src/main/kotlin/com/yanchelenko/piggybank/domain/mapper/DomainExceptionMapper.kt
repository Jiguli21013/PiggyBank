package com.yanchelenko.piggybank.domain.mapper

import com.yanchelenko.piggybank.exceptions.NotFoundException
import com.yanchelenko.piggybank.exceptions.ProductNotFoundException

fun Throwable.toUserMessage(): String = when (this) {
    is ProductNotFoundException -> "Такой товар не найден" //todo to res
    is NotFoundException -> "Элемент не найден" //todo to res
    else -> message ?: "Неизвестная ошибка" //todo to res
}
