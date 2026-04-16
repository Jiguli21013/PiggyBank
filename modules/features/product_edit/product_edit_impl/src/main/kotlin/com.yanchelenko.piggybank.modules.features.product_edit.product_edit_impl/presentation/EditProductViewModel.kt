package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductEffect
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductEvent
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.presentation.state.EditProductState
import com.yanchelenko.piggybank.modules.core.core_api.domain.mapper.toUserMessage
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.getData
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.updateDataSuccess
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetPricePerKgUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductWithCurrentVersionByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.base.ui_model.models.StableInstant
import kotlinx.datetime.Instant
import com.yanchelenko.piggybank.modules.core.core_api.domain.SaveProductVersionIfChangedResult
import com.yanchelenko.piggybank.modules.core.core_api.domain.SaveProductVersionIfChangedUseCase
import kotlinx.datetime.Clock
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.BaseDomainException
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.domain.ObserveCurrencyUseCase
import com.yanchelenko.piggybank.modules.core.core_api.models.GetProductWithCurrentVersionByIdResult
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val saveProductVersionIfChangedUseCase: SaveProductVersionIfChangedUseCase,
    private val getProductWithCurrentVersionByIdUseCase: GetProductWithCurrentVersionByIdUseCase,
    private val getPricePerKgUseCase: GetPricePerKgUseCase,
    private val observeCurrencyUseCase: ObserveCurrencyUseCase,
    private val logger: Logger,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<EditProductEvent, CommonUiState<EditProductState>, EditProductEffect>(
    initialState = CommonUiState.Initializing
) {

    init {
        val productId = savedStateHandle.get<Long>(AppDestination.ProductEditDestination.Meta.arguments.first().name)
            ?: error("productId is missing in SavedStateHandle") //todo error вынести куда-то ?

        logger.d(LOG_TAG, "Init with productId=${productId}")
        onEvent(event = EditProductEvent.LoadProductByProductId(productId = productId))
    }

    override fun onEvent(event: EditProductEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")

        when (event) {
            is EditProductEvent.LoadProductByProductId -> {
                logger.d(LOG_TAG, "Loading product by ID: ${event.productId}")
                loadProductByProductId(productId = event.productId)
            }

            is EditProductEvent.ProductNameChanged -> {
                logger.d(LOG_TAG, "ScannedProduct name changed: ${event.name}")
                setState {
                    updateDataSuccess {
                        copy(scannedProduct = scannedProduct.copy(productName = event.name))
                    }
                }
            }

            is EditProductEvent.WeightChanged -> {
                logger.d(LOG_TAG, "Weight changed: ${event.weight}")
                uiState.value.getData { state ->
                    val newPricePerKg = getPricePerKgUseCase(
                        weightGrams = event.weight,
                        price = state.scannedProduct.price
                    )
                    val updated = state.scannedProduct.copy(
                        weight = event.weight,
                        pricePerKg = newPricePerKg
                    )
                    setState { CommonUiState.Success(data = state.copy(scannedProduct = updated)) }
                }
            }

            is EditProductEvent.PriceInputChanged -> {
                logger.d(LOG_TAG, "Price input changed: ${event.input}")
                setState { updateDataSuccess { copy(priceInput = event.input) } }
            }

            is EditProductEvent.PriceChanged -> {
                logger.d(LOG_TAG, "Price changed: ${event.price}")
                uiState.value.getData { state ->
                    val newPricePerKg = getPricePerKgUseCase(
                        weightGrams = state.scannedProduct.weight,
                        price = event.price
                    )
                    val updated = state.scannedProduct.copy(
                        price = event.price,
                        pricePerKg = newPricePerKg
                    )
                    setState { CommonUiState.Success(data = state.copy(scannedProduct = updated)) }
                }
            }

            is EditProductEvent.SaveProduct -> {
                logger.d(LOG_TAG, "Save product requested")
                updateProductDB()
            }

            is EditProductEvent.GoBackToScanner -> {
                logger.d(LOG_TAG, "Navigate back requested")
                sendEffect { EditProductEffect.NavigateBack }
            }

            is EditProductEvent.ProductFoundInDB -> {
                logger.d(LOG_TAG, "ScannedProduct loaded from DB: ${event.product}")
                setState {
                    CommonUiState.Success(
                        data = EditProductState(
                            scannedProduct = event.product,
                            previousPrice = event.product.price,
                            previousWeight = event.product.weight,
                            isInScannedDB = true,
                            priceInput = if (event.product.price == 0.0) "" else event.product.price.toString()
                        )
                    )
                }
            }
        }
    }

    private fun loadProductByProductId(productId: Long) {
        logger.d(LOG_TAG, "Start DB lookup for productId=$productId")
        viewModelScope.launch {
            when (val result = getProductWithCurrentVersionByIdUseCase(productId)) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "ProductWithCurrentVersion loaded: ${result.data}")
                    val currentCurrency = observeCurrencyUseCase().first()

                    when (val productResult = result.data) {
                        is GetProductWithCurrentVersionByIdResult.Found -> {
                            val product = productResult.product.product
                            val currentVersion = productResult.product.currentVersion

                            onEvent(
                                EditProductEvent.ProductFoundInDB(
                                    product = com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel(
                                        productId = product.id,
                                        barcode = product.barcode,
                                        productName = product.productName,
                                        weight = currentVersion.weightGrams,
                                        price = currentVersion.price,
                                        pricePerKg = currentVersion.pricePerKg,
                                        formattedPrice = currentVersion.price.toCurrencyText(currency = currentCurrency),
                                        formattedPricePerKg = currentVersion.pricePerKg.toCurrencyText(currency = currentCurrency),
                                        addedAt = currentVersion.createdAt.toStable(),
                                    )
                                )
                            )
                        }

                        is GetProductWithCurrentVersionByIdResult.NotFound,
                        is GetProductWithCurrentVersionByIdResult.ProductWithoutCurrentVersion -> {
                            val message = "Продукт не найден" //todo move to resources
                            logger.e(LOG_TAG, "Error loading product: $message")
                            sendEffect { EditProductEffect.ShowMessage(message) }
                        }
                    }
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка" //todo
                    logger.e(LOG_TAG, "Error loading product: $message")
                    sendEffect { EditProductEffect.ShowMessage("Ошибка загрузки продукта: $message") } //todo to res
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Loading in progress...")
                }
            }
        }
    }

    private fun updateProductDB() {
        uiState.value.getData { state ->
            logger.d(LOG_TAG, "Start updating product: ${state.scannedProduct}")

            viewModelScope.launch {
                when (val result = saveProductVersionIfChangedUseCase(
                    params = SaveProductVersionIfChangedUseCase.Params(
                        barcode = state.scannedProduct.barcode,
                        productName = state.scannedProduct.productName,
                        weightGrams = state.scannedProduct.weight,
                        price = state.scannedProduct.price,
                        pricePerKg = state.scannedProduct.pricePerKg,
                        changedAt = Clock.System.now(),
                    )
                )) {
                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "Product version save result: ${result.data}")
                        setState { CommonUiState.Success(data = state) }

                        val message = when (result.data) {
                            is SaveProductVersionIfChangedResult.NewProductCreated -> "Продукт успешно сохранён"
                            is SaveProductVersionIfChangedResult.NewVersionCreated -> "Изменения успешно сохранены"
                            is SaveProductVersionIfChangedResult.NoChanges -> "Изменений не обнаружено"
                        }

                        sendEffect { EditProductEffect.ShowMessage(message) } //todo move to resources
                        sendEffect { EditProductEffect.NavigateBack }
                    }

                    is RequestResult.Error -> {
                        val message = result.error?.message ?: "Неизвестная ошибка" //todo
                        logger.e(LOG_TAG, "Error updating product: $message")
                        setState { CommonUiState.Error(message = message) }
                        sendEffect { EditProductEffect.ShowMessage("Ошибка при изменении: $message") } //todo
                    }

                    is RequestResult.InProgress -> {
                        logger.d(LOG_TAG, "Update in progress...")
                        setState { CommonUiState.Loading }
                    }
                }
            }
        }
    }

    private fun Double.toCurrencyText(currency: AppCurrency): String {
        return "$this ${currency.symbol}"
    }

    private fun Instant.toStable(): StableInstant {
        return StableInstant(epochMillis = toEpochMilliseconds())
    }

    private companion object {
        const val LOG_TAG = "EditProductVM"
    }
}
