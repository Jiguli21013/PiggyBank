package com.yanchelenko.piggybank.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yanchelenko.piggybank.core.database.dao.ProductDao
import com.yanchelenko.piggybank.core.database.mappers.toProduct
import com.yanchelenko.piggybank.core.database.mappers.toProductDbo
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public class ProductsRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    //private val logger: Logger
): ProductsRepository {

    override fun getPagedProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { productDao.getPaged() }
        ).flow.map { pagingData ->
            pagingData.map { it.toProduct() }
        }
    }

    //todo сделать пагинация по дням
    override fun getAllProductsFromDatabase(): Flow<List<Product>> {
        return productDao.observeAll()
            .map { list -> list.map { it.toProduct() } }
    }

    override suspend fun updateProductDatabase(product: Product): Result<Boolean> {
        return runCatching {
            val affectedRows = productDao.update(product = product.toProductDbo())
            Log.d("ProductDao", "Updating product with id=${product.id}")
            (affectedRows > 0)
        }
    }

    override suspend fun getProductById(productId: Long): Result<Product> {
        return runCatching {
            productDao.getById(productId = productId).toProduct()
        }
    }

    override suspend fun getProductByBarcode(barcode: String): Result<Product?> {
        return runCatching {
            productDao.getByBarcode(barcode = barcode)?.toProduct()
        }
    }

    override suspend fun saveProductToDatabase(product: Product): Result<Unit> {
        return runCatching {
            productDao.insert(product = product.toProductDbo(autoGenerateId = true))
            Unit
        }
    }

    override suspend fun deleteProductFromDatabase(product: Product): Result<Unit> {
        return runCatching {
            productDao.remove(product = product.toProductDbo())
            Unit
        }
    }

    private companion object {
        const val LOG_TAG = "ArticlesRepository"
    }

}
