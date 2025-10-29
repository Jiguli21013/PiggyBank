package com.yanchelenko.piggybank.modules.core.core_impl.data.mappers

import com.yanchelenko.piggybank.modules.core.core_api.models.Cart
import com.yanchelenko.piggybank.modules.core.core_api.models.CartStatus
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductInCart
import com.yanchelenko.piggybank.modules.core.database.dao.ProductInCartDBO
import com.yanchelenko.piggybank.modules.core.database.models.CartDBO
import kotlinx.datetime.Instant

fun CartDBO.toDomain(): Cart = Cart(
    id = id,
    storeId = storeId,
    totalItems = totalItems,
    totalPrice = totalPrice,
    status = if (status == "ACTIVE") CartStatus.ACTIVE else CartStatus.CLOSED,
    createdAt = Instant.fromEpochMilliseconds(createdAtEpochMs),
    closedAt = closedAtEpochMs?.let { Instant.fromEpochMilliseconds(it) } ?: Instant.fromEpochMilliseconds(createdAtEpochMs) // поидее null никогда не может быть
)

fun ProductInCartDBO?.toDomain(): ProductInCart =
    ProductInCart(
        quantity = this?.quantity ?: 0,
        itemId = this?.itemId
    )
