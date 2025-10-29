package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKey
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKeyToDate
import com.yanchelenko.piggybank.modules.base.ui_model.models.toStable
import com.yanchelenko.piggybank.modules.core.core_api.models.Cart
import com.yanchelenko.piggybank.modules.core.core_api.models.CartStatus
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.CartStatusUi
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.CartUiModel
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.ListItem
import kotlinx.datetime.TimeZone
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun PagingData<Cart>.toUiPagingData(): PagingData<CartUiModel> = this.map { it.toUi() }

fun PagingData<CartUiModel>.withDateHeaders(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): PagingData<ListItem> {
    val items = this.map { ListItem.HistoryCartItemUiModel(it) }

    return items.insertSeparators { before, after ->
        val beforeKey = before?.cart?.closedAt?.dayKey(timeZone)
        val afterKey  = after?.cart?.closedAt?.dayKey(timeZone)

        when {
            before == null && afterKey != null ->
                ListItem.DateHeaderUiModel(dayKeyToDate(afterKey))

            beforeKey != null && afterKey != null && beforeKey != afterKey ->
                ListItem.DateHeaderUiModel(dayKeyToDate(afterKey))

            else -> null
        }
    }
}

fun Cart.toUi(
    storeName: String? = null, // todo потом будет связка с таблицей store
): CartUiModel = CartUiModel(
        id = id,
        storeName = storeName,
        totalItems = this.totalItems,
        totalPrice = this.totalPrice,
        status = when (status) {
            CartStatus.ACTIVE -> CartStatusUi.ACTIVE
            CartStatus.CLOSED -> CartStatusUi.CLOSED
        },
        createdAt = createdAt.toStable(),
        closedAt = closedAt.toStable(),
        closedAtText = closedAt.toFormattedString()
    )

// todo move to base module
private val defaultFormatter: SimpleDateFormat by lazy {
    SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).apply {
        timeZone = java.util.TimeZone.getDefault()
    }
}

fun Instant.toFormattedString(
    formatter: SimpleDateFormat = defaultFormatter
): String = formatter.format(Date(this.toEpochMilliseconds()))

fun Instant?.toFormattedOrDash(
    formatter: SimpleDateFormat = defaultFormatter
): String = this?.let { formatter.format(Date(it.toEpochMilliseconds())) } ?: "—"
