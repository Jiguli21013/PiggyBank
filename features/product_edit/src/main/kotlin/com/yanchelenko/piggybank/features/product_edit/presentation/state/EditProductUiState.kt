package com.yanchelenko.piggybank.features.product_edit.presentation.state

import com.yanchelenko.piggybank.common.ui_models.ProductUiModel

data class EditProductUiState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false, //todo change name or remove

    val isSaved: Boolean = false,
    val errorMessage: String? = null,

    val uiProduct: ProductUiModel
)

fun EditProductUiState.updateUiProduct(
    block: ProductUiModel.() -> ProductUiModel
): EditProductUiState = copy(uiProduct = uiProduct.block())
