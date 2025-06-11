package com.yanchelenko.piggybank.fearues.history.domain

import androidx.paging.PagingData
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.domain.models.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    operator fun invoke(): Flow<PagingData<Product>> = repository.getPagedProducts()
}
