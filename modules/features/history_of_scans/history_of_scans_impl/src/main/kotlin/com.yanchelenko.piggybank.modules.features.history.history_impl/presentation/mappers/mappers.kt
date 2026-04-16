package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.StableInstant
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKey
import com.yanchelenko.piggybank.modules.base.ui_model.models.dayKeyToDate
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductWithCurrentVersion
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import kotlinx.datetime.TimeZone

fun PagingData<ProductWithCurrentVersion>.toUiPagingData(
    currency: AppCurrency,
): PagingData<ScannedProductUiModel> = this.map { item ->
    val product = item.product
    val currentVersion = item.currentVersion

    ScannedProductUiModel(
        productId = product.id,
        barcode = product.barcode,
        productName = product.productName,
        weight = currentVersion.weightGrams,
        price = currentVersion.price,
        pricePerKg = currentVersion.pricePerKg,
        formattedPrice = currentVersion.price.toCurrencyText(currency = currency),
        formattedPricePerKg = currentVersion.pricePerKg.toCurrencyText(currency = currency),
        addedAt = currentVersion.createdAt.toStable(),
    )
}

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
//todo вынести
private fun Double.toCurrencyText(currency: AppCurrency): String {
    return "$this ${currency.symbol}"
}

private fun kotlinx.datetime.Instant.toStable(): StableInstant {
    return StableInstant(epochMillis = toEpochMilliseconds())
}
