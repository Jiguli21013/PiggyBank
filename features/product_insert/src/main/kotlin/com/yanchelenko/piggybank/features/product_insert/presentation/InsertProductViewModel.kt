package com.yanchelenko.piggybank.features.product_insert.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import com.yanchelenko.piggybank.domain.exceptions.BaseDomainException
import com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase
import com.yanchelenko.piggybank.features.product_insert.domain.usecases.GetProductByBarcodeUseCase
import com.yanchelenko.piggybank.features.product_insert.domain.usecases.InsertNewProductUseCase
import com.yanchelenko.piggybank.features.product_insert.presentation.mappers.toDomain
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEffect
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductUiState
import com.yanchelenko.piggybank.features.product_insert.presentation.state.updateUiProduct
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggynank.core.ui.mapper.toUserMessage
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertProductScreenViewModel @Inject constructor(
    private val insertNewProductUseCase: InsertNewProductUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getPricePerKgUseCase: GetPricePerKgUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<InsertProductEvent, InsertProductUiState, InsertProductEffect>(
    initialState = InsertProductUiState(
        uiProduct = ProductUiModel(
            barcode = savedStateHandle.get<String>(AppDestination.InsertProduct.arguments.first().name) ?: ""
        )
    )
) {
    init {
        //todo вынести в евенты или еффекты ?
        state.uiProduct.barcode.let {
            onEvent(event = InsertProductEvent.LoadProductByBarcode(barcode = it))
        }
    }

    override fun onEvent(event: InsertProductEvent) {
        when (event) {
            is InsertProductEvent.LoadProductByBarcode -> {
                loadProductByBarcode(barcode = event.barcode)
            }
            is InsertProductEvent.ProductNameChanged -> {
                setState {
                    updateUiProduct {
                        copy(productName = event.name)
                    }
                }
            }
            is InsertProductEvent.WeightChanged -> {
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
            is InsertProductEvent.PriceChanged -> {
                setState {
                    val pricePerKg = getPricePerKgUseCase(
                        weightGrams = uiProduct.weight,
                        price = event.price
                    )
                    updateUiProduct {
                        copy(
                            price = event.price,
                            pricePerKg = pricePerKg
                        )
                    }

                }
            }
            is InsertProductEvent.CurrencyChanged -> {
                setState {
                    updateUiProduct {
                        copy()
                    }
                }
            }
            is InsertProductEvent.SaveProduct -> {
                insertProductToDB()
            }
            is InsertProductEvent.GoBackToScanner -> {
                setState {
                    copy(isSaved = false)
                }
                sendEffect { InsertProductEffect.NavigateBackToScanner }
            }
            is InsertProductEvent.ProductFoundInDB -> {
                setState {
                    updateUiProduct {
                        copy(
                            productId = event.product.productId,
                            barcode = event.product.barcode,
                            productName = event.product.productName,
                            weight = event.product.weight,
                            price = event.product.price,
                            pricePerKg = event.product.pricePerKg,
                            addedAt = event.product.addedAt
                        )
                    }
                }
            }
        }
    }

    private fun loadProductByBarcode(barcode: String) {
        viewModelScope.launch {
            when (val result = getProductByBarcodeUseCase(barcode)) {
                is RequestResult.Success -> {
                    val uiProduct = result.data.toUi()
                    onEvent(InsertProductEvent.ProductFoundInDB(product = uiProduct))
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка" //todo поидее можно убрать

                    sendEffect { InsertProductEffect.ShowMessage(message) }
                }

                is RequestResult.InProgress -> Unit //todo loader
            }
        }
    }

    private fun insertProductToDB() {
        viewModelScope.launch {
            setState { copy(isSaving = true, errorMessage = null) }

            when (val result = insertNewProductUseCase(product = state.toDomain())) {
                is RequestResult.Success -> {
                    setState { copy(isSaving = false, isSaved = true) }
                    sendEffect { InsertProductEffect.ShowMessage(message = "Продукт успешно сохранён") }
                    sendEffect { InsertProductEffect.NavigateBackToScanner }
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Ошибка при сохранении"

                    setState { copy(isSaving = false, errorMessage = message) }
                    sendEffect { InsertProductEffect.ShowMessage(message) }
                }

                is RequestResult.InProgress -> Unit
            }
        }
    }
}
