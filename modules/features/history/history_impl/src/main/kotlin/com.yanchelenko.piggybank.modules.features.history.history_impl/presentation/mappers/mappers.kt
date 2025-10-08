package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.modules.base.ui_model.mapper.toUi
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKey
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKeyToDate
import com.yanchelenko.piggybank.modules.core.core_api.models.Product
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import kotlinx.datetime.TimeZone

fun PagingData<Product>.toUiPagingData(): PagingData<ProductUiModel> = this.map { it.toUi() }

fun PagingData<ProductUiModel>.withDateHeaders(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): PagingData<ListItem> {
    val items = this.map { ListItem.ProductItemUiModel(it) }

    return items.insertSeparators { before, after ->
        val beforeKey = before?.product?.addedAt?.dayKey(timeZone)
        val afterKey  = after?.product?.addedAt?.dayKey(timeZone)

        when {
            before == null && afterKey != null ->
                ListItem.DateHeaderUiModel(dayKeyToDate(afterKey))

            beforeKey != null && afterKey != null && beforeKey != afterKey ->
                ListItem.DateHeaderUiModel(dayKeyToDate(afterKey))

            else -> null
        }
    }
}
