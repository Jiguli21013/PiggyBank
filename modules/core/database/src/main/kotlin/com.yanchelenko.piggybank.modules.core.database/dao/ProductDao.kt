package com.yanchelenko.piggybank.modules.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.paging.PagingSource
import com.yanchelenko.piggybank.modules.core.database.models.ProductDBO
import com.yanchelenko.piggybank.modules.core.database.models.ProductWithCurrentVersionProjection

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    suspend fun getByBarcode(barcode: String): ProductDBO?

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getById(productId: Long): ProductDBO?
    //todo сделать более читаемым
    @Query(
        """
        SELECT
            p.id AS product_id,
            p.barcode AS product_barcode,
            p.productName AS product_name,
            p.createdAtEpochMs AS product_created_at_epoch_ms,
            v.id AS version_id,
            v.productId AS version_product_id,
            v.weightGrams AS version_weight_grams,
            v.price AS version_price,
            v.pricePerKg AS version_price_per_kg,
            v.createdAtEpochMs AS version_created_at_epoch_ms,
            v.isCurrent AS version_is_current
        FROM products p
        INNER JOIN product_versions v
            ON v.productId = p.id
        WHERE v.isCurrent = 1
        ORDER BY p.id DESC
        """
    )
    fun getPagedProductsWithCurrentVersion():
            PagingSource<Int, ProductWithCurrentVersionProjection>

    @Insert
    suspend fun insert(product: ProductDBO): Long

    @Query("UPDATE products SET productName = :productName WHERE id = :productId")
    suspend fun updateProductName(
        productId: Long,
        productName: String,
    ): Int

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteById(productId: Long): Int
}
