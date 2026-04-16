package com.yanchelenko.piggybank.modules.core.core_api.models

data class ProductWithCurrentVersion(
    val product: Product,
    val currentVersion: ProductVersion,
)
