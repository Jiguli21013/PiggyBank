package com.yanchelenko.piggybank.fearues.history.domain

import androidx.paging.PagingData
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import com.yanchelneko.piggybank.common.core_utils.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

// этот кажется лишний, просто перевызов метода
class GetPagedProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) {
    operator fun invoke(): Flow<PagingData<Product>> {
        logger.d(LOG_TAG, "invoke() called — starting to collect paged products")
        return repository.getPagedProducts()
            .onStart {
                logger.d(LOG_TAG, "Flow started")
            }
            .onEach {
                logger.d(LOG_TAG, "New PagingData emitted")
            }
            .catch { throwable ->
                logger.e(LOG_TAG, "Error while collecting paging data: ${throwable.message}")
                throw throwable
            }
    }

    private companion object {
        const val LOG_TAG = "GetPagedProductsUseCase"
    }
}
