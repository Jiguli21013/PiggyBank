package com.yanchelenko.piggybank.modules.core.core_impl.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.core.core_api.repository.ScannedProductsRepository
import com.yanchelenko.piggybank.modules.core.database.dao.ScannedProductDao
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toScannedProduct
import com.yanchelenko.piggybank.modules.core.core_impl.data.mappers.toProductDbo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScannedScannedProductsRepositoryImpl @Inject constructor(
    private val scannedProductDao: ScannedProductDao,
    private val logger: Logger
) : ScannedProductsRepository {

    override fun getPagedScannedProducts(): Flow<PagingData<ScannedProduct>> {
        logger.d(LOG_TAG, "Fetching paged scanned products from DB")
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = { scannedProductDao.getPagedScannedProducts() }
        ).flow.map { pagingData ->
            pagingData.map { it.toScannedProduct() }
        }
    }
    //todo delete
    override fun getAllProductsFromDatabase(): Flow<List<ScannedProduct>> {
        logger.d(LOG_TAG, "Observing all scanned products from DB")
        return scannedProductDao.observeAll()
            .map { list ->
                logger.d(LOG_TAG, "Fetched ${list.size} products")
                list.map { it.toScannedProduct() }
            }
    }

    override suspend fun updateProductDatabase(scannedProduct: ScannedProduct): Result<Boolean> {
        logger.d(LOG_TAG, "Updating scannedProduct: ${scannedProduct.id}")
        return runCatching {
            val affectedRows = scannedProductDao.update(scannedProduct.toProductDbo())
            logger.d(LOG_TAG, "Update affected $affectedRows rows")
            affectedRows > 0
        }.onFailure {
            logger.e(LOG_TAG, "Failed to update scannedProduct ${scannedProduct.id}: ${it.message}")
        }
    }

    override suspend fun getScannedProductById(productId: Long): Result<ScannedProduct> {
        logger.d(LOG_TAG, "Getting scanned product by id: $productId")
        return runCatching {
            scannedProductDao.getById(productId = productId).toScannedProduct()
        }.onFailure {
            logger.e(LOG_TAG, "Failed to get scanned product by id=$productId: ${it.message}")
        }
    }

    override suspend fun getScannedProductByBarcode(barcode: String): Result<ScannedProduct?> {
        logger.d(LOG_TAG, "Getting scanned product by barcode: $barcode")
        return runCatching {
            scannedProductDao.getByBarcode(barcode = barcode)?.toScannedProduct()
        }.onFailure {
            logger.e(LOG_TAG, "Failed to get scanned product by barcode=$barcode: ${it.message}")
        }
    }

    override suspend fun saveScannedProductToDatabase(scannedProduct: ScannedProduct): Result<Unit> {
        logger.d(LOG_TAG, "Saving new scannedProduct: ${scannedProduct.productName}")
        return runCatching {
            scannedProductDao.insert(scannedProduct.toProductDbo(autoGenerateId = true))
            logger.d(LOG_TAG, "ScannedProduct saved: ${scannedProduct.productName}")
        }.onFailure {
            logger.e(LOG_TAG, "Failed to save scannedProduct ${scannedProduct.productName}: ${it.message}")
        }
    }

    override suspend fun deleteScannedProductFromDatabase(scannedProduct: ScannedProduct): Result<Unit> {
        logger.d(LOG_TAG, "Deleting scannedProduct: ${scannedProduct.id}")
        return runCatching {
            scannedProductDao.remove(scannedProduct.toProductDbo())
            logger.d(LOG_TAG, "ScannedProduct deleted: ${scannedProduct.id}")
        }.onFailure {
            logger.e(LOG_TAG, "Failed to delete scannedProduct ${scannedProduct.id}: ${it.message}")
        }
    }

    private companion object {
        const val LOG_TAG = "ScannedProductsRepository"
    }
}
