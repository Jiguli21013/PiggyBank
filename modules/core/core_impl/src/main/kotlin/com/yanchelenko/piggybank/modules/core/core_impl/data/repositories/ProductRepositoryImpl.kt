package com.yanchelenko.piggybank.modules.core.core_impl.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductVersion
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductWithCurrentVersion
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductRepository
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toDomain
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toDbo
import com.yanchelenko.piggybank.modules.core.database.dao.ProductDao
import com.yanchelenko.piggybank.modules.core.database.dao.ProductVersionDao
import com.yanchelenko.piggybank.modules.core.database.models.ProductWithCurrentVersionProjection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map as mapFlow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productVersionDao: ProductVersionDao,
) : ProductRepository {

    override suspend fun getProductById(productId: Long): Result<Product?> {
        return runCatching {
            productDao.getById(productId = productId)?.toDomain()
        }
    }

    override suspend fun getProductByBarcode(barcode: String): Result<Product?> {
        return runCatching {
            productDao.getByBarcode(barcode = barcode)?.toDomain()
        }
    }

    override suspend fun getCurrentVersion(productId: Long): Result<ProductVersion?> {
        return runCatching {
            productVersionDao.getCurrentByProductId(productId = productId)?.toDomain()
        }
    }

    override suspend fun getVersionHistory(productId: Long): Result<List<ProductVersion>> {
        return runCatching {
            productVersionDao.getAllByProductId(productId = productId)
                .map { it.toDomain() }
        }
    }

    override fun getPagedProductsWithCurrentVersion(): Flow<PagingData<ProductWithCurrentVersion>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                productDao.getPagedProductsWithCurrentVersion()
            }
        ).flow.mapFlow { pagingData: PagingData<ProductWithCurrentVersionProjection> ->
            pagingData.map { projection ->
                projection.toDomain()
            }
        }
    }

    override suspend fun createProduct(product: Product): Result<Long> {
        return runCatching {
            productDao.insert(product = product.toDbo())
        }
    }

    override suspend fun createProductVersion(version: ProductVersion): Result<Long> {
        return runCatching {
            productVersionDao.insert(version = version.toDbo())
        }
    }

    override suspend fun updateProductName(
        productId: Long,
        productName: String,
    ): Result<Boolean> {
        return runCatching {
            productDao.updateProductName(
                productId = productId,
                productName = productName,
            ) > 0
        }
    }

    override suspend fun clearCurrentVersion(productId: Long): Result<Boolean> {
        return runCatching {
            productVersionDao.clearCurrentVersion(productId = productId) > 0
        }
    }

    override suspend fun deleteProduct(productId: Long): Result<Boolean> {
        return runCatching {
            productDao.deleteById(productId = productId) > 0
        }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}
