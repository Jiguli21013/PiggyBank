package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models

import com.yanchelenko.piggybank.modules.base.ui_model.models.StableInstant

data class CartUiModel(
    val id: Long,
    val storeName: String?,

    val totalItems: Int,
    val totalPrice: Double, //todo mapper

    val status: CartStatusUi,
    val createdAt: StableInstant,
    val closedAt: StableInstant,

    val closedAtText: String
)

enum class CartStatusUi {
    ACTIVE,
    CLOSED
}
