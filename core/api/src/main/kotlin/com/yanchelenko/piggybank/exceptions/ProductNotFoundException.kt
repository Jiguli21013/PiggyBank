package com.yanchelenko.piggybank.exceptions

class ProductNotFoundException(
    val barcode: String
) : NotFoundException(message = "Продукт с баркодом $barcode не найден")
