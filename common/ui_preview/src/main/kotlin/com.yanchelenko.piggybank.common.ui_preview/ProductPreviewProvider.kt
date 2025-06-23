package com.yanchelenko.piggybank.common.ui_preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import kotlinx.datetime.Clock

class ProductPreviewProvider : PreviewParameterProvider<ProductUiModel> {
    override val values = sequenceOf(
        ProductUiModel(
            productId = 1L,
            barcode = "1234567890",
            productName = "Хлеб",
            weight = 500.0,
            price = 40.0,
            pricePerKg = 80.0,
            addedAt = Clock.System.now()
        )
    )
}
