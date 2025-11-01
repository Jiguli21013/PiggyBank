package com.yanchelenko.piggybank.modules.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yanchelenko.piggybank.modules.core.database.models.ScannedProductDBO

@Dao
interface ScannedProductDao {

    @Query("SELECT * FROM scanned_products ORDER BY addedAt DESC")
    fun getPagedScannedProducts(): PagingSource<Int, ScannedProductDBO>

    @Query("SELECT * FROM scanned_products WHERE barcode = :barcode LIMIT 1")
    suspend fun getByBarcode(barcode: String): ScannedProductDBO?

    @Query("SELECT * FROM scanned_products WHERE id = :scannedProductId LIMIT 1")
    suspend fun getById(scannedProductId: Long): ScannedProductDBO

    @Insert
    suspend fun insert(scannedProduct: ScannedProductDBO)

    /**
     *  0 — nothing was updated (no such id)
     *  1 — one row was updated
     */
    @Update
    suspend fun update(scannedProduct: ScannedProductDBO): Int

    @Delete
    suspend fun remove(scannedProduct: ScannedProductDBO)

    @Query("DELETE FROM scanned_products")
    suspend fun clean()

}
