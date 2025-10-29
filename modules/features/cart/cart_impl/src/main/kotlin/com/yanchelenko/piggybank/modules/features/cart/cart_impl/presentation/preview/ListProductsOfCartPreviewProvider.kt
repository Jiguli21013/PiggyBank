package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel

class ListProductsOfCartPreviewProvider :
    PreviewParameterProvider<List<ProductOfCartUiModel>> {

    override val values: Sequence<List<ProductOfCartUiModel>> = sequenceOf(
        listOf(
            ProductOfCartUiModel(
                cartItemId = 4,
                productId = 1,
                name = "Хлеб бородинский",
                barcode = "4601234567890",
                formattedPrice = "₽45.00",
                formattedPricePerKg = null,
                quantityText = "1",
                weightText = null,
                unitPrice = "14",
                totalPriceText = "₽45.00"
            ),
            ProductOfCartUiModel(
                cartItemId = 7,
                productId = 2,
                name = "Яблоки Гала",
                barcode = "4600987654321",
                formattedPrice = "₽150.00",
                formattedPricePerKg = "₽150.00 / кг",
                quantityText = "6",
                weightText = "700 г",
                unitPrice = "12",
                totalPriceText = "₽105.00"
            ),
            ProductOfCartUiModel(
                cartItemId = 8,
                productId = 3,
                name = "Молоко 2.5%",
                barcode = "4601112233445",
                formattedPrice = "₽89.00",
                formattedPricePerKg = null,
                quantityText = "2",
                weightText = null,
                unitPrice = "15",
                totalPriceText = "₽178.00"
            )
        )
    )
}
