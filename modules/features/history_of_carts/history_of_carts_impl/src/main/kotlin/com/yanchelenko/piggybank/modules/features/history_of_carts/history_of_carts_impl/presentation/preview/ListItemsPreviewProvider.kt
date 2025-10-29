package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.modules.base.ui_model.models.StableInstant
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.CartStatusUi
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.CartUiModel
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.ListItem
import kotlinx.datetime.LocalDate
import java.math.BigDecimal

class ListItemPreviewProvider : PreviewParameterProvider<List<ListItem>> {

    private val today: LocalDate = LocalDate(2024, 10, 6)
    private val yesterday: LocalDate = LocalDate(2024, 10, 5)

    override val values: Sequence<List<ListItem>> = sequenceOf(
        listOf(
            ListItem.DateHeaderUiModel(date = today),
            ListItem.HistoryCartItemUiModel(
                cart = CartUiModel(
                    id = 1,
                    storeName = "Everyday Mart 1",
                    totalItems = 4,
                    totalPrice = 10.64,
                    status = CartStatusUi.CLOSED,
                    createdAt = StableInstant.DISTANT_PAST,
                    closedAt = StableInstant.DISTANT_FUTURE,
                    closedAtText = "18 июня"
                )
            ),
            ListItem.HistoryCartItemUiModel(
                cart = CartUiModel(
                    id = 2,
                    storeName = "Everyday Mart 2",
                    totalItems = 5,
                    totalPrice = 12.75,
                    status = CartStatusUi.CLOSED,
                    createdAt = StableInstant.DISTANT_PAST,
                    closedAt = StableInstant.DISTANT_FUTURE,
                    closedAtText = "5 июня"
                )
            ),
            ListItem.DateHeaderUiModel(date = yesterday),
            ListItem.HistoryCartItemUiModel(
                cart = CartUiModel(
                    id = 3,
                    storeName = "Everyday Mart 3",
                    totalItems = 7,
                    totalPrice = 16.50,
                    status = CartStatusUi.CLOSED,
                    createdAt = StableInstant.DISTANT_PAST,
                    closedAt = StableInstant.DISTANT_FUTURE,
                    closedAtText = "4 июля"
                )
            )
        )
    )
}
