package com.yanchelenko.piggybank.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.extensions.toLocalDateInSystemZone
import com.yanchelenko.piggybank.models.Product
import com.yanchelenko.piggybank.presentation.models.ListItem

fun PagingData<Product>.withDateHeaders(): PagingData<ListItem> {
    return this
        .map { ListItem.ProductItemUiModel(it) }
        .insertSeparators { before, after ->
            val beforeDate = before?.product?.addedAt?.toLocalDateInSystemZone()
            val afterDate = after?.product?.addedAt?.toLocalDateInSystemZone()

            return@insertSeparators when {
                before == null && afterDate != null -> ListItem.DateHeaderUiModel(afterDate)

                beforeDate != null && afterDate != null && beforeDate != afterDate ->
                    ListItem.DateHeaderUiModel(afterDate)

                else -> null
            }
        }
}
