package com.yanchelenko.piggybank.modules.core.core_api.models

import kotlinx.datetime.Instant
import java.math.BigDecimal

/**
 * Pure domain model representing a shopping cart.
 *
 * @property id Unique identifier of the cart.
 * @property storeId Identifier of the store where the cart was created (nullable if none selected).
 * @property totalItems Current items amount in the cart
 * @property totalPrice Current price of all cart.
 * @property status Current cart status — "ACTIVE" or "CLOSED".
 * @property createdAt Timestamp when the cart was created.
 * @property closedAt Timestamp when the cart was closed (nullable if still active).
 */
data class Cart(
    val id: Long,
    val storeId: Long?,
    val totalItems: Int,
    val totalPrice: Double,
    val status: CartStatus,
    val createdAt: Instant,
    val closedAt: Instant
)

/**
 * Enum representing the cart's lifecycle state.
 */
enum class CartStatus {
    ACTIVE,
    CLOSED
}
