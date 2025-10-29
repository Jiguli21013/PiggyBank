package com.yanchelenko.piggybank.modules.base.ui_kit.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.toStable
import kotlinx.datetime.Clock

class ProductPreviewProvider : PreviewParameterProvider<ScannedProductUiModel> {
    override val values = sequenceOf(
        ScannedProductUiModel(
            productId = 1L,
            barcode = "1234567890",
            productName = "Хлеб",
            weight = 500,
            price = 40.0,
            pricePerKg = 80.0,
            addedAt = Clock.System.now().toStable()
        )
    )
}
