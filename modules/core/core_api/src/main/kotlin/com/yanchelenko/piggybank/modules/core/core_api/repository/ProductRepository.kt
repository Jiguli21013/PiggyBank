package com.yanchelenko.piggybank.modules.core.core_api.repository

import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductVersion
import androidx.paging.PagingData
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductWithCurrentVersion
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductById(productId: Long): Result<Product?>

    suspend fun getProductByBarcode(barcode: String): Result<Product?>

    suspend fun getCurrentVersion(productId: Long): Result<ProductVersion?>

    suspend fun getVersionHistory(productId: Long): Result<List<ProductVersion>>

    fun getPagedProductsWithCurrentVersion(): Flow<PagingData<ProductWithCurrentVersion>>

    suspend fun createProduct(product: Product): Result<Long>

    suspend fun createProductVersion(version: ProductVersion): Result<Long>

    suspend fun updateProductName(productId: Long, productName: String, ): Result<Boolean>

    suspend fun clearCurrentVersion(productId: Long): Result<Boolean>

    suspend fun deleteProduct(productId: Long): Result<Boolean>
}