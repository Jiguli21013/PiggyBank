package com.yanchelenko.piggybank.modules.core.core_api.models

/**
 * One cart line item.
 *
 * Semantics:
 * - [unitPrice]: specified price for this item.
 * - [isWeightImportant]: defines if weight affects pricing.
 *   - If true → price depends on weight (e.g., price per kilogram).
 *   - If false → weight is only informational, price is per unit/pack.
 */
 data class ProductOfCart(
     val cartItemId: Long,
     val productId: Long?, // nullable: linked product may be deleted later
     val name: String,
     val barcode: String,
     val unitPrice: Double,
     val isWeightImportant: Boolean,
     val weightGrams: Int?,
     val quantity: Int,
 )
