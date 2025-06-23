package com.yanchelenko.piggybank.fearues.history.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.common.extensions.toLocalDateInSystemZone
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.domain.models.Product
import com.yanchelenko.piggybank.fearues.history.presentation.components.ListItem

fun PagingData<Product>.toUiPagingData(): PagingData<ProductUiModel> = this.map { it.toUi() }

fun PagingData<ProductUiModel>.withDateHeaders(): PagingData<ListItem> {
    return this
        .map { ListItem.ProductItem(it) }
        .insertSeparators { before, after ->
            val beforeDate = before?.product?.addedAt?.toLocalDateInSystemZone()
            val afterDate = after?.product?.addedAt?.toLocalDateInSystemZone()
            //val beforeDate = before?.product?.addedAt?.value?.toLocalDateTime(TimeZone.currentSystemDefault())?.date
            //val afterDate = after?.product?.addedAt?.value?.toLocalDateTime(TimeZone.currentSystemDefault())?.date

           
            return@insertSeparators when {
                before == null && afterDate != null -> ListItem.DateHeader(afterDate)

                beforeDate != null && afterDate != null && beforeDate != afterDate ->
                    ListItem.DateHeader(afterDate)

                else -> null
            }
        }
}
