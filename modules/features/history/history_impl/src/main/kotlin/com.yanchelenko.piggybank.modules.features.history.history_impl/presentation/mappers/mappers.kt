package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.modules.base.infrastructure.extensions.toLocalDateInSystemZone
import com.yanchelenko.piggybank.modules.base.ui_model.mapper.toUi
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem

fun PagingData<Product>.toUiPagingData(): PagingData<ProductUiModel> = this.map { it.toUi() }

fun PagingData<ProductUiModel>.withDateHeaders(): PagingData<ListItem> {
    return this
        .map { ListItem.ProductItem(it) }
        .insertSeparators { before, after ->
            val beforeDate = before?.product?.addedAt?.toLocalDateInSystemZone()
            val afterDate = after?.product?.addedAt?.toLocalDateInSystemZone()
           
            return@insertSeparators when {
                before == null && afterDate != null -> ListItem.DateHeader(afterDate)

                beforeDate != null && afterDate != null && beforeDate != afterDate ->
                    ListItem.DateHeader(afterDate)

                else -> null
            }
        }
}
