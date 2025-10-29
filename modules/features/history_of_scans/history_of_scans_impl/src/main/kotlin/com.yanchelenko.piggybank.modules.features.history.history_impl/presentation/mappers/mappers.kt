package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toUi
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKey
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKeyToDate
import com.yanchelenko.piggybank.modules.core.core_api.models.ScannedProduct
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import kotlinx.datetime.TimeZone

fun PagingData<ScannedProduct>.toUiPagingData(): PagingData<ScannedProductUiModel> = this.map { it.toUi() }

fun PagingData<ScannedProductUiModel>.withDateHeaders(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): PagingData<ListItem> {
    val items = this.map { ListItem.ScannedProductItemUiModel(it) }

    return items.insertSeparators { before, after ->
        val beforeKey = before?.scannedProduct?.addedAt?.dayKey(timeZone)
        val afterKey  = after?.scannedProduct?.addedAt?.dayKey(timeZone)

        when {
            before == null && afterKey != null ->
                ListItem.DateHeaderUiModel(dayKeyToDate(afterKey))

            beforeKey != null && afterKey != null && beforeKey != afterKey ->
                ListItem.DateHeaderUiModel(dayKeyToDate(afterKey))

            else -> null
        }
    }
}
