package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ListItemPreviewProvider : PreviewParameterProvider<List<ListItem>> {
    override val values: Sequence<List<ListItem>> = sequenceOf(
        listOf(
            ListItem.DateHeader(
                date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            ),
            ListItem.ProductItem(
                ProductUiModel(
                    productId = 1L,
                    barcode = "1234567890",
                    productName = "Молоко",
                    weight = 1000.0,
                    price = 90.0,
                    pricePerKg = 90.0,
                    addedAt = Instant.DISTANT_PAST
                )
            ),
            ListItem.ProductItem(
                ProductUiModel(
                    productId = 2L,
                    barcode = "0987654321",
                    productName = "Хлеб",
                    weight = 500.0,
                    price = 40.0,
                    pricePerKg = 80.0,
                    addedAt = Instant.DISTANT_PAST
                )
            )
        )
    )
}
