package com.yanchelenko.piggybank.fearues.history.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.common.extensions.toLocalDateInSystemZone
import com.yanchelenko.piggybank.common.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.fearues.history.presentation.components.ListItem

fun PagingData<Product>.toUiPagingData(): PagingData<ProductUiModel> = this.map { it.toUi() }

fun PagingData<ProductUiModel>.withDateHeaders(): PagingData<ListItem> {
    return this
        .map { ListItem.ProductItem(it) }
        .insertSeparators { before, after ->
            val beforeDate = before?.product?.addedAt?.toLocalDateInSystemZone()
            val afterDate = after?.product?.addedAt?.toLocalDateInSystemZone()
            println("Separator check: before = $beforeDate | after = $afterDate--addedAt---${after?.product?.addedAt}")
            return@insertSeparators when {

                // Вставка перед первым элементом
                before == null && afterDate != null -> ListItem.DateHeader(afterDate)

                // Вставка между элементами разных дат
                beforeDate != null && afterDate != null && beforeDate != afterDate ->
                    ListItem.DateHeader(afterDate)

                else -> null
            }
        }
}

