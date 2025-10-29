package com.yanchelenko.piggybank.modules.core.core_api.repository

import androidx.paging.PagingData
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import kotlinx.coroutines.flow.Flow

interface ScannedProductsRepository {
    /**
     * Получение продуктов с использованием пагинации.
     */
    fun getPagedScannedProducts(): Flow<PagingData<ScannedProduct>>

    /**
     * Получение продукта по ID.
     */
    suspend fun getScannedProductById(productId: Long): Result<ScannedProduct>

    /**
     * Получение продукта по штрихкоду.
     */
    suspend fun getScannedProductByBarcode(barcode: String): Result<ScannedProduct?>

    /**
     * Сохранение продукта.
     */
    suspend fun saveScannedProductToDatabase(scannedProduct: ScannedProduct): Result<Unit>

    /**
     * Обновление продукта.
     */
    suspend fun updateProductDatabase(scannedProduct: ScannedProduct): Result<Boolean>

    /**
     * Получение всех продуктов без пагинации.
     */
    //todo delete
    fun getAllProductsFromDatabase(): Flow<List<ScannedProduct>>

    /**
     * Удаление продукта.
     */
    suspend fun deleteScannedProductFromDatabase(scannedProduct: ScannedProduct): Result<Unit>
}
