package com.yanchelenko.piggybank.features.product_insert.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
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
import com.yanchelneko.piggybank.common.core_utils.Logger
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertProductScreenViewModel @Inject constructor(
    private val insertNewProductUseCase: InsertNewProductUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getPricePerKgUseCase: GetPricePerKgUseCase,
    logger: Logger,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<InsertProductEvent, InsertProductUiState, InsertProductEffect>(
    logger = logger,
    initialState = InsertProductUiState(
        uiProduct = ProductUiModel(
            barcode = savedStateHandle.get<String>(AppDestination.InsertProduct.arguments.first().name) ?: ""
        )
    )
) {

    init {
        //todo вынести в евенты или еффекты ?
        logger.d(LOG_TAG, "Init with barcode: ${state.uiProduct.barcode}")
        state.uiProduct.barcode.let {
            onEvent(event = InsertProductEvent.LoadProductByBarcode(barcode = it))
        }
    }

    override fun onEvent(event: InsertProductEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")

        when (event) {
            is InsertProductEvent.LoadProductByBarcode -> {
                logger.d(LOG_TAG, "Loading product by barcode: ${event.barcode}")
                loadProductByBarcode(barcode = event.barcode)
            }

            is InsertProductEvent.ProductNameChanged -> {
                logger.d(LOG_TAG, "Product name changed: ${event.name}")
                setState {
                    updateUiProduct {
                        copy(productName = event.name)
                    }
                }
            }
            //todo вместо соpy сделать copyIfChanged
            is InsertProductEvent.WeightChanged -> {
                logger.d(LOG_TAG, "Weight changed: ${event.weight}")
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
                logger.d(LOG_TAG, "Price changed: ${event.price}")
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
                logger.d(LOG_TAG, "Currency changed")
                setState {
                    updateUiProduct {
                        copy()
                    }
                }
            }

            is InsertProductEvent.SaveProduct -> {
                logger.d(LOG_TAG, "Save product triggered")
                insertProductToDB()
            }

            is InsertProductEvent.GoBackToScanner -> {
                logger.d(LOG_TAG, "Go back to scanner requested")
                setState {
                    copy(isSaved = false)
                }
                sendEffect { InsertProductEffect.NavigateBackToScanner }
            }

            is InsertProductEvent.ProductFoundInDB -> {
                logger.d(LOG_TAG, "Product found in DB: ${event.product}")
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
        logger.d(LOG_TAG, "Start loading product from DB for barcode=$barcode")
        viewModelScope.launch {
            when (val result = getProductByBarcodeUseCase(barcode)) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "Product loaded successfully: ${result.data}")
                    val uiProduct = result.data.toUi()
                    onEvent(InsertProductEvent.ProductFoundInDB(product = uiProduct))
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка"
                    logger.e(LOG_TAG, "Failed to load product: $message")
                    sendEffect { InsertProductEffect.ShowMessage(message) }
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Loading in progress...")
                }
            }
        }
    }

    private fun insertProductToDB() {
        logger.d(LOG_TAG, "Start inserting product to DB: ${state.uiProduct}")
        viewModelScope.launch {
            setState { copy(isSaving = true, errorMessage = null) }

            when (val result = insertNewProductUseCase(product = state.toDomain())) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "Product successfully inserted")
                    setState { copy(isSaving = false, isSaved = true) }
                    sendEffect { InsertProductEffect.ShowMessage(message = "Продукт успешно сохранён") }
                    sendEffect { InsertProductEffect.NavigateBackToScanner }
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Ошибка при сохранении"
                    logger.e(LOG_TAG, "Insert failed: $message")
                    setState { copy(isSaving = false, errorMessage = message) }
                    sendEffect { InsertProductEffect.ShowMessage(message) }
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Insert in progress...")
                }
            }
        }
    }

    private companion object {
        const val LOG_TAG = "InsertProductVM"
    }
}
