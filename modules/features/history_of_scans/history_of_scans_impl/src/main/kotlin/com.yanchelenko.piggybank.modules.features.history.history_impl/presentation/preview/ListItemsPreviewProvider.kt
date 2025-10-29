package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.toStable
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

class ListItemPreviewProvider : PreviewParameterProvider<List<ListItem>> {

    private val today: LocalDate = LocalDate(2024, 10, 6)
    private val yesterday: LocalDate = LocalDate(2024, 10, 5)

    override val values: Sequence<List<ListItem>> = sequenceOf(
        listOf(
            ListItem.DateHeaderUiModel(date = today),
            ListItem.ScannedProductItemUiModel(
                ScannedProductUiModel(
                    productId = 1L,
                    barcode = "1234567890",
                    productName = "Молоко",
                    weight = 1000,
                    price = 90.0,
                    pricePerKg = 90.0,
                    addedAt = Instant.DISTANT_PAST.toStable()
                )
            ),
            ListItem.ScannedProductItemUiModel(
                ScannedProductUiModel(
                    productId = 2L,
                    barcode = "0987654321",
                    productName = "Хлеб",
                    weight = 500,
                    price = 40.0,
                    pricePerKg = 80.0,
                    addedAt = Instant.DISTANT_PAST.toStable()
                )
            ),
            ListItem.DateHeaderUiModel(date = yesterday),
            ListItem.ScannedProductItemUiModel(
                ScannedProductUiModel(
                    productId = 2L,
                    barcode = "0987654321",
                    productName = "Хлеб",
                    weight = 400,
                    price = 45.0,
                    pricePerKg = 70.0,
                    addedAt = Instant.DISTANT_PAST.toStable()
                )
            )
        )
    )
}
