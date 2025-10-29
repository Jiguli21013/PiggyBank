package com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.modules.base.ui_model.models.StableInstant
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.CartStatusUi
import com.yanchelenko.piggybank.modules.features.history_of_carts.history_of_carts_impl.presentation.models.CartUiModel

class CartPreviewProvider : PreviewParameterProvider<CartUiModel> {
    override val values = sequenceOf(
        CartUiModel(
            id = 1L,
            storeName = "24/7",
            totalItems = 5,
            totalPrice = 10.5,
            status = CartStatusUi.CLOSED,
            createdAt = StableInstant.DISTANT_PAST,
            closedAt = StableInstant.DISTANT_FUTURE,
            closedAtText = "18 июня"
        )
    )
}
