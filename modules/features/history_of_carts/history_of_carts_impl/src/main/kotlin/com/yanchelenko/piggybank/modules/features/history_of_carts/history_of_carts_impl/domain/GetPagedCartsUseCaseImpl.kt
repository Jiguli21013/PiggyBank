package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.domain

import androidx.paging.PagingData
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.models.Cart
import com.yanchelenko.piggybank.modules.core.core_api.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

//todo наследование от интерфейса?
class GetPagedCartsUseCaseImpl @Inject constructor(
    private val repository: CartRepository,
    private val logger: Logger
) {
    operator fun invoke(): Flow<PagingData<Cart>> {
        logger.d(LOG_TAG, "invoke() called — starting to collect paged products")
        return repository.getPagedCarts()
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
        const val LOG_TAG = "GetPagedCartsUseCaseImpl"
    }
}
