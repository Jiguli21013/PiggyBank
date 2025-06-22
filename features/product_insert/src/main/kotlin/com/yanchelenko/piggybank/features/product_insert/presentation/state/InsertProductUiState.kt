package com.yanchelenko.piggybank.features.product_insert.presentation.state

import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel

data class InsertProductUiState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false, //todo change name or remove

    val isSaved: Boolean = false,
    val errorMessage: String? = null,

    val uiProduct: ProductUiModel //todo val uiProduct: ProductUiModel? = null
)

fun InsertProductUiState.updateUiProduct(
    block: ProductUiModel.() -> ProductUiModel
): InsertProductUiState = copy(uiProduct = uiProduct.block())
