package com.yanchelenko.piggybank.modules.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yanchelenko.piggybank.modules.core.database.models.ProductDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    suspend fun getAll(): List<ProductDBO>

    @Query("SELECT * FROM products ORDER BY addedAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getPagedManual(limit: Int, offset: Int): List<ProductDBO>

    @Query("SELECT * FROM products ORDER BY addedAt DESC")
    fun getPaged(): PagingSource<Int, ProductDBO>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getCount(): Int

    @Query("SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    suspend fun getByBarcode(barcode: String): ProductDBO?

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getById(productId: Long): ProductDBO

    @Query("SELECT * FROM products")
    fun observeAll(): Flow<List<ProductDBO>>

    @Insert
    suspend fun insert(products: List<ProductDBO>)

    @Insert
    suspend fun insert(product: ProductDBO)

    /**
     * 	0 — ничего не обновлено (нет такого id)
     * 	1 — одна строка обновлена
     */
    @Update
    suspend fun update(product: ProductDBO): Int

    @Delete
    suspend fun remove(products: List<ProductDBO>)

    @Delete
    suspend fun remove(product: ProductDBO)

    @Query("DELETE FROM products")
    suspend fun clean()
}
