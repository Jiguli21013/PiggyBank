package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.model

import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct

/**
 * Pure domain DTO for initializing the InsertProduct flow.
 * - [product] may be null if it wasn't found; the VM maps this to a UI placeholder.
 */
data class ProductInitResultDomain(
    val barcode: String,
    val product: ScannedProduct?,
    val isInCart: Boolean
)
