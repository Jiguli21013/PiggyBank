package com.yanchelenko.piggybank.modules.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.yanchelenko.piggybank.modules.core.database.models.ProductVersionDBO

@Dao
interface ProductVersionDao {

    @Query(
        """
        SELECT * FROM product_versions
        WHERE productId = :productId AND isCurrent = 1
        LIMIT 1
        """
    )
    suspend fun getCurrentByProductId(productId: Long): ProductVersionDBO?

    @Query(
        """
        SELECT * FROM product_versions
        WHERE productId IN (:productIds)
          AND isCurrent = 1
        """
    )
    suspend fun getCurrentByProductIds(productIds: List<Long>): List<ProductVersionDBO>

    @Query(
        """
        SELECT * FROM product_versions
        WHERE productId = :productId
        ORDER BY createdAtEpochMs DESC
        """
    )
    suspend fun getAllByProductId(productId: Long): List<ProductVersionDBO>

    @Insert
    suspend fun insert(version: ProductVersionDBO): Long

    @Update
    suspend fun update(version: ProductVersionDBO): Int

    @Query(
        """
        UPDATE product_versions
        SET isCurrent = 0
        WHERE productId = :productId AND isCurrent = 1
        """
    )
    suspend fun clearCurrentVersion(productId: Long): Int
}