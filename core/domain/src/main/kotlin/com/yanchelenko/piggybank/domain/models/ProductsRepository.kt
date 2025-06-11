package com.yanchelenko.piggybank.domain.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    /**
     * Получение продуктов с использованием пагинации.
     */
    fun getPagedProducts(): Flow<PagingData<Product>>

    /**
     * Получение продукта по ID.
     */
    suspend fun getProductById(productId: Long): Result<Product>

    /**
     * Получение продукта по штрихкоду.
     */
    suspend fun getProductByBarcode(barcode: String): Result<Product?>

    /**
     * Сохранение продукта.
     */
    suspend fun saveProductToDatabase(product: Product): Result<Unit>

    /**
     * Обновление продукта.
     */
    suspend fun updateProductDatabase(product: Product): Result<Boolean>

    /**
     * Получение всех продуктов без пагинации.
     */
    fun getAllProductsFromDatabase(): Flow<List<Product>>

    /**
     * Удаление продукта.
     */
    suspend fun deleteProductFromDatabase(product: Product): Result<Unit>
}
