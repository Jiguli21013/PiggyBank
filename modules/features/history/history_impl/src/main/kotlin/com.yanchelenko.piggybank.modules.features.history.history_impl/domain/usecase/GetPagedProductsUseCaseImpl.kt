package com.yanchelenko.piggybank.modules.features.history.history_impl.domain.usecase

import androidx.paging.PagingData
import com.yanchelenko.piggybank.modules.core.core_api.logger.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.core.core_api.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetPagedProductsUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository,
    private val logger: Logger
) {
    operator fun invoke(): Flow<PagingData<Product>> {
        logger.d(LOG_TAG, "invoke() called — starting to collect paged products")
        return repository.getPagedProducts()
            .onStart {
                logger.d(LOG_TAG, "Flow started")
            }
            .onEach { // useless
                logger.d(LOG_TAG, "New PagingData emitted")
            }
            .catch { throwable ->
                logger.e(LOG_TAG, "Error while collecting paging data: ${throwable.message}")
                throw throwable
            }
    }

    private companion object {
        const val LOG_TAG = "GetPagedProductsUseCaseImpl"
    }
}
