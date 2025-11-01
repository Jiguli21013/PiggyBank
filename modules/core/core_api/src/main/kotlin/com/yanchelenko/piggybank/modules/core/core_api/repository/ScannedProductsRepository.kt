package com.yanchelenko.piggybank.modules.core.core_api.repository

import androidx.paging.PagingData
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import kotlinx.coroutines.flow.Flow

interface ScannedProductsRepository {
    /**
     * Fetch products using pagination.
     */
    fun getPagedScannedProducts(): Flow<PagingData<ScannedProduct>>

    /**
     * Fetch product by ID.
     */
    suspend fun getScannedProductById(productId: Long): Result<ScannedProduct>

    /**
     * Fetch product by barcode.
     */
    suspend fun getScannedProductByBarcode(barcode: String): Result<ScannedProduct?>

    /**
     * Save product to database.
     */
    suspend fun saveScannedProductToDatabase(scannedProduct: ScannedProduct): Result<Unit>

    /**
     * Update product in database.
     */
    suspend fun updateProductDatabase(scannedProduct: ScannedProduct): Result<Boolean>

    /**
     * Delete product from database.
     */
    suspend fun deleteScannedProductFromDatabase(scannedProduct: ScannedProduct): Result<Unit>
}
