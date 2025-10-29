package com.yanchelenko.piggybank.modules.core.core_impl.data.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.CartTotals
import com.yanchelenko.piggybank.modules.core.database.dao.CartTotalsRawDBO

internal fun CartTotalsRawDBO.toDomain(): CartTotals =
    CartTotals(
        itemsCount = itemsCount,
        totalWeightGrams = totalWeightGrams,
        totalPrice = totalPrice
    )
