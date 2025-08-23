package com.yanchelenko.piggybank.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.models.Product
import kotlinx.datetime.Clock

// TODO тут его точно не должно быть, но использований много, надо подумать
class ProductPreviewProvider : PreviewParameterProvider<Product> {
    override val values = sequenceOf(
        Product(
            id = 1L,
            barcode = "1234567890",
            productName = "Хлеб",
            weight = 500.0,
            price = 40.0,
            pricePerKg = 80.0,
            addedAt = Clock.System.now()
        )
    )
}
