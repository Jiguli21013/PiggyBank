package com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.updateDataSuccess
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toCartDomain
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toDomain
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteProductFromCartUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetPricePerKgUseCase
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase.AddProductToCartUseCase
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.domain.usecase.InitInsertProductStateInteractor
import com.yanchelenko.piggybank.modules.features.product_insert.product_insert_impl.presentation.state.InsertProductState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertProductScreenViewModel @Inject constructor(
    private val initInsertProductStateInteractor: InitInsertProductStateInteractor,
    private val getPricePerKgUseCase: GetPricePerKgUseCase,
    private val insertNewProductUseCase: InsertNewProductUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val deleteProductFromCartUseCase: DeleteProductFromCartUseCase,
    private val logger: Logger,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<InsertProductEvent, CommonUiState<InsertProductState>, InsertProductEffect>(
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
                logger.d(LOG_TAG, "ScannedProduct name changed: ${event.name}")
                uiState.value.getData { state ->
                    setState {
                        CommonUiState.Success(
                            data = state.copy(
                                scannedProduct = state.scannedProduct.copy(
                                    productName = event.name
                                )
                            )
                        )
                    }
                }
            }

            is InsertProductEvent.WeightChanged -> {
                logger.d(LOG_TAG, "Weight changed: ${event.weight}")
                uiState.value.getData { state ->
                    setState {
                        val pricePerKg = getPricePerKgUseCase(
                            weightGrams = event.weight,
                            price = state.scannedProduct.price
                        )
                        CommonUiState.Success(
                            data = state.copy(
                                scannedProduct = state.scannedProduct.copy(
                                    weight = event.weight,
                                    pricePerKg = pricePerKg
                                )
                            )
                        )
                    }
                }
            }

            is InsertProductEvent.WeightNotImportantChanged -> {
                logger.d(LOG_TAG, "Weight Not Important Changed: ${event.isChecked}")

            }

            is InsertProductEvent.PriceChanged -> {
                logger.d(LOG_TAG, "Price changed: ${event.price}")
                uiState.value.getData { state ->
                    setState {
                        val pricePerKg = getPricePerKgUseCase(
                            weightGrams = state.scannedProduct.weight,
                            price = event.price
                        )
                        CommonUiState.Success(
                            data = state.copy(
                                scannedProduct = state.scannedProduct.copy(
                                    price = event.price,
                                    pricePerKg = pricePerKg
                                )
                            )
                        )
                    }
                }
            }

            is InsertProductEvent.PriceInputChanged -> {
                logger.d(LOG_TAG, "Price input changed: ${event.input}")
                setState { updateDataSuccess { copy(priceInput = event.input) } }
            }

            is InsertProductEvent.CurrencyChanged -> {
                logger.d(LOG_TAG, "Currency changed")
                //todo
            }

            is InsertProductEvent.IncreaseQuantity -> {
                logger.d(LOG_TAG, "Increase quantity")
                uiState.value.getData { state ->
                    val newQty = (state.quantity + 1).coerceAtMost(99)
                    if (newQty != state.quantity) {
                        setState { CommonUiState.Success(data = state.copy(quantity = newQty)) }
                    }
                }
            }

            is InsertProductEvent.DecreaseQuantity -> {
                logger.d(LOG_TAG, "Decrease quantity")
                uiState.value.getData { state ->
                    val newQty = (state.quantity - 1).coerceAtLeast(minimumValue = 1)
                    if (newQty != state.quantity) {
                        setState { CommonUiState.Success(data = state.copy(quantity = newQty)) }
                    }
                }
            }

            is InsertProductEvent.AddToCart -> {
                logger.d(LOG_TAG, "Add to cart event")
                val ensured = (uiState.value as? CommonUiState.Success)?.data
                if (ensured != null) {
                    val clamped = ensured.copy(quantity = ensured.quantity.coerceIn(1, 99))
                    setState { CommonUiState.Success(data = clamped) }
                }
                insertProductToCartDB()
            }

            is InsertProductEvent.RemoveFromCart -> {
                logger.d(LOG_TAG, "Remove from cart event")
                deleteProductFromCartDB()
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

            is InsertProductEvent.ProductFoundInScannedDB -> {
                logger.d(LOG_TAG, "ScannedProduct found in DB: ${event.state}")
                setState { CommonUiState.Success(data = event.state.copy(isInScannedDB = true)) }
            }

            is InsertProductEvent.ProductNotFoundInScannedDB -> {
                logger.d(LOG_TAG, "ScannedProduct not found in DB")
                setState { CommonUiState.Success(data = event.state.copy(isInScannedDB = false)) }
            }
        }
    }

    private fun loadProductByBarcode(barcode: String) {
        logger.d(LOG_TAG, "Start loading product from DB for barcode=$barcode")

        viewModelScope.launch(Dispatchers.IO) {  //todo dispatcher
            when (val result = initInsertProductStateInteractor(barcode = barcode)) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "ScannedProduct loaded successfully: ${result.data}")
                    if (result.data.product != null) {
                        onEvent(InsertProductEvent.ProductFoundInScannedDB(state = result.data.toUiState()))
                    } else {
                        onEvent(InsertProductEvent.ProductNotFoundInScannedDB(state = InsertProductState(scannedProduct = ScannedProductUiModel(barcode = barcode))))
                    }
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка" //todo
                    logger.e(LOG_TAG, "Failed to load product: $message")
                    sendEffect { InsertProductEffect.ShowMessage(message) }
                    onEvent(InsertProductEvent.ProductNotFoundInScannedDB(state = InsertProductState(scannedProduct = ScannedProductUiModel(barcode = barcode))))
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Loading in progress...")
                    setState { CommonUiState.Loading }
                }
            }
        }
    }

    private fun insertProductToDB() {
        uiState.value.getData { state ->
            logger.d(LOG_TAG, "Start inserting product to DB: ${state.scannedProduct}")
            viewModelScope.launch(Dispatchers.IO) { //todo provider dispatcher io
                when (val result = insertNewProductUseCase(scannedProduct = state.scannedProduct.toDomain())) {
                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "ScannedProduct successfully inserted")
                        setState { CommonUiState.Success(data = state) }
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

    private fun insertProductToCartDB() {
        uiState.value.getData { state ->
            logger.d(LOG_TAG, "Start inserting product to cart: ${state.scannedProduct} x${state.quantity}")
            viewModelScope.launch(Dispatchers.IO) { //todo provider dispatcher io
                if (!state.isInScannedDB) {
                    logger.d(LOG_TAG, "Scanned not in DB → saving before cart insert: ${state.scannedProduct}")
                    when (val save = insertNewProductUseCase(scannedProduct = state.scannedProduct.toDomain())) {
                        is RequestResult.Success -> {
                            val newId = save.data
                            logger.d(LOG_TAG, "Scanned saved successfully with id=$newId")
                            setState {
                                CommonUiState.Success(
                                    data = state.copy(
                                        isInScannedDB = true,
                                        scannedProduct = state.scannedProduct.copy(productId = newId)
                                    )
                                )
                            }
                        }
                        is RequestResult.Error -> {
                            val msg = (save.error as? BaseDomainException)?.toUserMessage()
                                ?: save.error?.message
                                ?: "Ошибка при сохранении отсканированного товара"
                            logger.e(LOG_TAG, "Saving scanned failed: $msg")
                            setState { CommonUiState.Error(message = msg) }
                            sendEffect { InsertProductEffect.ShowMessage(message = msg) }
                            return@launch
                        }
                        is RequestResult.InProgress -> {
                            logger.d(LOG_TAG, "Saving scanned in progress...")
                            setState { CommonUiState.Loading }
                            return@launch
                        }
                    }
                }

                val latestState = (uiState.value as? CommonUiState.Success)?.data ?: state
                when (val result = addProductToCartUseCase(productOfCart = latestState.scannedProduct.toCartDomain(quantity = latestState.quantity))) {
                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "ProductOfCart successfully added to cart")
                        setState {
                            CommonUiState.Success(
                                data = latestState.copy(
                                    cartItemId = result.data,
                                    isInCart = true,
                                    isInScannedDB = true
                                )
                            )
                        }
                        sendEffect { InsertProductEffect.ShowMessage(message = "Продукт успешно добавлен в корзину") } //todo
                    }

                    is RequestResult.Error -> {
                        val message = (result.error as? BaseDomainException)?.toUserMessage()
                            ?: result.error?.message
                            ?: "Ошибка при добавлении в корзину" //todo
                        logger.e(LOG_TAG, "Insert to cart failed: $message")
                        setState { CommonUiState.Error(message = message) }
                        sendEffect { InsertProductEffect.ShowMessage(message = message) }
                    }

                    is RequestResult.InProgress -> {
                        logger.d(LOG_TAG, "Insert to cart in progress...")
                        setState { CommonUiState.Loading }
                    }
                }
            }
        }
    }

    private fun deleteProductFromCartDB() {
        uiState.value.getData { state ->
            logger.d(LOG_TAG, "Start removing product from cart cartItemId: ${state.cartItemId}")
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = deleteProductFromCartUseCase(productOfCartId = state.cartItemId ?: return@launch)) {
                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "Product successfully removed from cart")
                        // Mark item as not in cart in the UI state
                        setState { CommonUiState.Success(data = state.copy(cartItemId = null, isInCart = false)) }
                        sendEffect { InsertProductEffect.ShowMessage(message = "Продукт удалён из корзины") } //todo
                    }
                    is RequestResult.Error -> {
                        val message = (result.error as? BaseDomainException)?.toUserMessage()
                            ?: result.error?.message
                            ?: "Ошибка при удалении из корзины" //todo
                        logger.e(LOG_TAG, "Remove from cart failed: $message")
                        setState { CommonUiState.Error(message = message) }
                        sendEffect { InsertProductEffect.ShowMessage(message = message) }
                    }
                    is RequestResult.InProgress -> {
                        logger.d(LOG_TAG, "Remove from cart in progress...")
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
