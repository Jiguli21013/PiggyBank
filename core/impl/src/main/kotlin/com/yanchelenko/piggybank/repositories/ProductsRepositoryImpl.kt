package com.yanchelenko.piggybank.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yanchelenko.piggybank.dao.ProductDao
import com.yanchelenko.piggybank.logger.Logger
import com.yanchelenko.piggybank.mappers.toProduct
import com.yanchelenko.piggybank.mappers.toProductDbo
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val logger: Logger
) : ProductsRepository {

    override fun getPagedProducts(): Flow<PagingData<Product>> {
        logger.d(LOG_TAG, "Fetching paged products from DB")
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = { productDao.getPaged() }
        ).flow.map { pagingData ->
            pagingData.map { it.toProduct() }
        }
    }

    override fun getAllProductsFromDatabase(): Flow<List<Product>> {
        logger.d(LOG_TAG, "Observing all products from DB")
        return productDao.observeAll()
            .map { list ->
                logger.d(LOG_TAG, "Fetched ${list.size} products")
                list.map { it.toProduct() }
            }
    }

    override suspend fun updateProductDatabase(product: Product): Result<Boolean> {
        logger.d(LOG_TAG, "Updating product: ${product.id}")
        return runCatching {
            val affectedRows = productDao.update(product.toProductDbo())
            logger.d(LOG_TAG, "Update affected $affectedRows rows")
            affectedRows > 0
        }.onFailure {
            logger.e(LOG_TAG, "Failed to update product ${product.id}: ${it.message}")
        }
    }

    override suspend fun getProductById(productId: Long): Result<Product> {
        logger.d(LOG_TAG, "Getting product by id: $productId")
        return runCatching {
            productDao.getById(productId).toProduct()
        }.onFailure {
            logger.e(LOG_TAG, "Failed to get product by id=$productId: ${it.message}")
        }
    }

    override suspend fun getProductByBarcode(barcode: String): Result<Product?> {
        logger.d(LOG_TAG, "Getting product by barcode: $barcode")
        return runCatching {
            productDao.getByBarcode(barcode)?.toProduct()
        }.onFailure {
            logger.e(LOG_TAG, "Failed to get product by barcode=$barcode: ${it.message}")
        }
    }

    override suspend fun saveProductToDatabase(product: Product): Result<Unit> {
        logger.d(LOG_TAG, "Saving new product: ${product.productName}")
        return runCatching {
            productDao.insert(product.toProductDbo(autoGenerateId = true))
            logger.d(LOG_TAG, "Product saved: ${product.productName}")
        }.onFailure {
            logger.e(LOG_TAG, "Failed to save product ${product.productName}: ${it.message}")
        }
    }

    override suspend fun deleteProductFromDatabase(product: Product): Result<Unit> {
        logger.d(LOG_TAG, "Deleting product: ${product.id}")
        return runCatching {
            productDao.remove(product.toProductDbo())
            logger.d(LOG_TAG, "Product deleted: ${product.id}")
        }.onFailure {
            logger.e(LOG_TAG, "Failed to delete product ${product.id}: ${it.message}")
        }
    }

    private companion object {
        const val LOG_TAG = "ProductsRepository"
    }
}
