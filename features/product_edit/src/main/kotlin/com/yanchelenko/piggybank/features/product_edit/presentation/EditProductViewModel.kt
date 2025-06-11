package com.yanchelenko.piggybank.features.product_edit.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import com.yanchelenko.piggybank.domain.exceptions.BaseDomainException
import com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase
import com.yanchelenko.piggybank.domain.usecases.GetProductByIdUseCase
import com.yanchelenko.piggybank.features.product_edit.domain.usecases.UpdateProductUseCase
import com.yanchelenko.piggybank.features.product_edit.presentation.mappers.toDomain
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEffect
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductUiState
import com.yanchelenko.piggybank.features.product_edit.presentation.state.updateUiProduct
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggynank.core.ui.mapper.toUserMessage
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val updateProductUseCase: UpdateProductUseCase,
    private val getProductByProductIdUseCase: GetProductByIdUseCase,
    private val getPricePerKgUseCase: GetPricePerKgUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<EditProductEvent, EditProductUiState, EditProductEffect>(
    initialState = EditProductUiState(
        uiProduct = ProductUiModel(
            barcode = "",
            productId = savedStateHandle.get<Long>(AppDestination.EditProduct.arguments.first().name) ?: 0L
        )
    )
) {

    init {
        state.uiProduct.productId.let {
            onEvent(event = EditProductEvent.LoadProductByProductId(productId = it))
        }
    }

    override fun onEvent(event: EditProductEvent) {
        when (event) {
            is EditProductEvent.LoadProductByProductId -> {
                loadProductByProductId(productId = event.productId)
            }
            is EditProductEvent.ProductNameChanged -> {
                setState {
                    updateUiProduct {
                        copy(productName = event.name)
                    }
                }
            }
            is EditProductEvent.WeightChanged -> {
                setState {
                    val pricePerKg = getPricePerKgUseCase(
                        weightGrams = event.weight,
                        price = uiProduct.price
                    )
                    updateUiProduct {
                        copy(
                            weight = event.weight,
                            pricePerKg = pricePerKg
                        )
                    }
                }
            }
            is EditProductEvent.PriceChanged -> {
                setState {
                    val pricePerKg = getPricePerKgUseCase(
                        weightGrams = event.price,
                        price = uiProduct.price
                    )
                    updateUiProduct {
                        copy(
                            price = event.price,
                            pricePerKg = pricePerKg
                        )
                    }
                }
            }
            is EditProductEvent.SaveProduct -> {
                updateProductDB()
            }
            is EditProductEvent.GoBackToScanner -> {
                sendEffect { EditProductEffect.NavigateBack }
            }
            is EditProductEvent.ProductFoundInDB -> {
                setState { copy(uiProduct = event.product) }
            }
        }
    }

    private fun loadProductByProductId(productId: Long) {
        viewModelScope.launch {
            when (val result = getProductByProductIdUseCase(productId)) {
                is RequestResult.Success -> {
                    onEvent(EditProductEvent.ProductFoundInDB(product = result.data.toUi()))
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка"
                    sendEffect { EditProductEffect.ShowMessage("Ошибка загрузки продукта: $message") }
                }

                is RequestResult.InProgress -> {

                }
            }
        }
    }

    private fun updateProductDB() {
        viewModelScope.launch {
            setState { copy(isSaving = true, errorMessage = null) }

            when (val result = updateProductUseCase(product = state.toDomain())) {
                is RequestResult.Success -> {
                    setState { copy(isSaving = false, isSaved = true) }
                    sendEffect { EditProductEffect.ShowMessage("Продукт успешно изменён") }
                    sendEffect { EditProductEffect.NavigateBack }
                }

                is RequestResult.Error -> {
                    setState { copy(isSaving = false, errorMessage = result.error?.message) }
                    sendEffect { EditProductEffect.ShowMessage("Ошибка при изменении: ${result.error?.message}") }
                }

                is RequestResult.InProgress -> Unit
            }
        }
    }
}
