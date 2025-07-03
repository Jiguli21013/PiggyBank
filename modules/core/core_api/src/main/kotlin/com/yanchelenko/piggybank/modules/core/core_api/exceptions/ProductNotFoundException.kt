package com.yanchelenko.piggybank.modules.core.core_api.exceptions

class ProductNotFoundException(
    val barcode: String
) : NotFoundException(message = "Продукт с баркодом $barcode не найден") //todo to res
