package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase.GetProductByBarcodeUseCase
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase.InsertNewProductUseCase
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductEffect
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductEvent
import com.yanchelenko.piggybank.modules.core.core_api.domain.mapper.toUserMessage
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.BaseDomainException
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.getData
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.ui_model.mapper.toDomain
import com.yanchelenko.piggybank.modules.base.ui_model.mapper.toUi
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductUiModel
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetPricePerKgUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertProductScreenViewModel @Inject constructor(
    private val insertNewProductUseCase: InsertNewProductUseCase,
    private val getProductByBarcodeUseCase: GetProductByBarcodeUseCase,
    private val getPricePerKgUseCase: GetPricePerKgUseCase,
    private val logger: Logger,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<InsertProductEvent, CommonUiState<ProductUiModel>, InsertProductEffect>(
    initialState = CommonUiState.Initializing
) {

    init {
        val barcode = savedStateHandle.get<String>(AppDestination.InsertProductDestination.Meta.arguments.first().name)
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
                uiState.value.getData { product ->
                    setState { CommonUiState.Success(data = product.copy(productName = event.name)) }
                }
            }

            is InsertProductEvent.WeightChanged -> {
                logger.d(LOG_TAG, "Weight changed: ${event.weight}")
                uiState.value.getData { product ->
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
                uiState.value.getData { product ->
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
                //todo валидация полей
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

            is InsertProductEvent.ProductNotFoundInDB -> {
                logger.d(LOG_TAG, "Product not found in DB")
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
                    onEvent(InsertProductEvent.ProductNotFoundInDB(product = ProductUiModel(barcode = barcode)))
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Loading in progress...")
                }
            }
        }
    }

    private fun insertProductToDB() {
        uiState.value.getData { product ->
            logger.d(LOG_TAG, "Start inserting product to DB: $product")
            viewModelScope.launch(Dispatchers.IO) { //todo provider dispatcher io
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
                        sendEffect { InsertProductEffect.ShowMessage(message = message) }
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
