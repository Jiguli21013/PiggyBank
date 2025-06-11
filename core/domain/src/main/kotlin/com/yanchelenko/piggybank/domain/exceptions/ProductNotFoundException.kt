package com.yanchelenko.piggybank.domain.exceptions

class ProductNotFoundException(
    val barcode: String
) : NotFoundException(message = "Продукт с баркодом $barcode не найден") //todo to res
