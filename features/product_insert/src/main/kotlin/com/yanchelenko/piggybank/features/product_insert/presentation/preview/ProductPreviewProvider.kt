package com.yanchelenko.piggybank.features.product_insert.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductUiState
import kotlinx.datetime.Clock
//todo в общий модуль
class InsertProductPreviewProvider : PreviewParameterProvider<InsertProductUiState> {
    override val values = sequenceOf(
        InsertProductUiState(
            uiProduct = ProductUiModel(
                productId = 1L,
                barcode = "1234567890",
                productName = "Хлеб",
                weight = 500.0,
                price = 40.0,
                pricePerKg = 80.0,
                addedAt = Clock.System.now()
            ),
            isLoading = false,
            isSaving = false,
            isSaved = false,
        )
    )
}
