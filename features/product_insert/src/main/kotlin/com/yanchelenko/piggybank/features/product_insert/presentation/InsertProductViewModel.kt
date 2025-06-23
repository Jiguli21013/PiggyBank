package com.yanchelenko.piggybank.features.product_insert.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toDomain
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.common.ui_state.CommonUiState
import com.yanchelenko.piggybank.common.ui_state.getDataOrLog
import com.yanchelenko.piggybank.domain.exceptions.BaseDomainException
import com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase
import com.yanchelenko.piggybank.features.product_insert.domain.usecases.GetProductByBarcodeUseCase
import com.yanchelenko.piggybank.features.product_insert.domain.usecases.InsertNewProductUseCase
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEffect
import com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent
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
) : BaseViewModel<InsertProductEvent, CommonUiState<ProductUiModel>, InsertProductEffect>(
    logger = logger,
    initialState = CommonUiState.Initializing
) {

    init {
        val barcode = savedStateHandle.get<String>(AppDestination.InsertProduct.arguments.first().name)
            ?: error("barcode is missing in SavedStateHandle") //todo error вынести куда-то ?
        logger.d(LOG_TAG, "Init with barcode: $barcode")
        onEvent(event = InsertProductEvent.LoadProductByBarcode(barcode = barcode))
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
                uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
                    setState { CommonUiState.Success(data = product.copy(productName = event.name)) }
                }
            }

            is InsertProductEvent.WeightChanged -> {
                logger.d(LOG_TAG, "Weight changed: ${event.weight}")
                uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
                    setState {
                        val pricePerKg = getPricePerKgUseCase(
                            weightGrams = event.weight,
                            price = product.price
                        )
                        CommonUiState.Success(
                            data = product.copy(
                                weight = event.weight,
                                pricePerKg = pricePerKg
                            )
                        )
                    }
                }
            }

            is InsertProductEvent.PriceChanged -> {
                logger.d(LOG_TAG, "Price changed: ${event.price}")
                uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
                    setState {
                        val pricePerKg = getPricePerKgUseCase(
                            weightGrams = product.weight,
                            price = event.price
                        )
                        CommonUiState.Success(
                            data = product.copy(
                                price = event.price,
                                pricePerKg = pricePerKg
                            )
                        )
                    }
                }
            }

            is InsertProductEvent.CurrencyChanged -> {
                logger.d(LOG_TAG, "Currency changed")
                //todo
            }

            is InsertProductEvent.SaveProduct -> {
                logger.d(LOG_TAG, "Save product triggered")
                insertProductToDB()
            }

            is InsertProductEvent.GoBackToScanner -> {
                logger.d(LOG_TAG, "Go back to scanner requested")
                sendEffect { InsertProductEffect.NavigateBackToScanner }
            }

            is InsertProductEvent.ProductFoundInDB -> {
                logger.d(LOG_TAG, "Product found in DB: ${event.product}")
                setState { CommonUiState.Success(data = event.product) }
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
                        ?: "Неизвестная ошибка" //todo
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
        uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
            logger.d(LOG_TAG, "Start inserting product to DB: $product")
            viewModelScope.launch {
                when (val result = insertNewProductUseCase(product = product.toDomain())) {
                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "Product successfully inserted")
                        setState { CommonUiState.Success(data = product) }
                        sendEffect { InsertProductEffect.ShowMessage(message = "Продукт успешно сохранён") } //todo
                        sendEffect { InsertProductEffect.NavigateBackToScanner }
                    }

                    is RequestResult.Error -> {
                        val message = (result.error as? BaseDomainException)?.toUserMessage()
                            ?: result.error?.message
                            ?: "Ошибка при сохранении" //todo
                        logger.e(LOG_TAG, "Insert failed: $message")
                        setState { CommonUiState.Error(message = message) }
                        sendEffect { InsertProductEffect.ShowMessage(message) }
                    }

                    is RequestResult.InProgress -> {
                        logger.d(LOG_TAG, "Insert in progress...")
                        setState { CommonUiState.Loading }
                    }
                }
            }
        }
    }

    private companion object {
        const val LOG_TAG = "InsertProductVM"
    }
}
