package com.yanchelenko.piggybank.features.product_edit.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toDomain
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.common.ui_state.CommonUiState
import com.yanchelenko.piggybank.common.ui_state.updateDataSuccess
import com.yanchelenko.piggybank.common.ui_state.getDataOrLog
import com.yanchelenko.piggybank.domain.exceptions.BaseDomainException
import com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase
import com.yanchelenko.piggybank.domain.usecases.GetProductByIdUseCase
import com.yanchelenko.piggybank.features.product_edit.domain.usecases.UpdateProductUseCase
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEffect
import com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggynank.core.ui.mapper.toUserMessage
import com.yanchelneko.piggybank.common.core_utils.Logger
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val updateProductUseCase: UpdateProductUseCase,
    private val getProductByProductIdUseCase: GetProductByIdUseCase,
    private val getPricePerKgUseCase: GetPricePerKgUseCase,
    logger: Logger,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<EditProductEvent, CommonUiState<ProductUiModel>, EditProductEffect>(
    logger = logger,
    initialState = CommonUiState.Initializing
) {

    init {
        val productId = savedStateHandle.get<Long>(AppDestination.EditProduct.arguments.first().name)
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
                logger.d(LOG_TAG, "Product name changed: ${event.name}")
                setState {
                    updateDataSuccess(LOG_TAG, logger) {
                        copy(productName = event.name)
                    }
                }
            }

            is EditProductEvent.WeightChanged -> {
                logger.d(LOG_TAG, "Weight changed: ${event.weight}")
                uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
                    val newPricePerKg = getPricePerKgUseCase(
                        weightGrams = event.weight,
                        price = product.price
                    )
                    setState {
                        CommonUiState.Success(
                            data = product.copy(
                                weight = event.weight,
                                pricePerKg = newPricePerKg
                            )
                        )
                    }
                }
            }

            is EditProductEvent.PriceChanged -> {
                logger.d(LOG_TAG, "Price changed: ${event.price}")
                uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
                    val newPricePerKg = getPricePerKgUseCase(
                        weightGrams = product.weight,
                        price = event.price
                    )
                    setState {
                        CommonUiState.Success(
                            data = product.copy(
                                price = event.price,
                                pricePerKg = newPricePerKg
                            )
                        )
                    }
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
                logger.d(LOG_TAG, "Product loaded from DB: ${event.product}")
                setState { CommonUiState.Success(data = event.product) }
            }
        }
    }

    private fun loadProductByProductId(productId: Long) {
        logger.d(LOG_TAG, "Start DB lookup for productId=$productId")
        viewModelScope.launch {
            when (val result = getProductByProductIdUseCase(productId)) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "Product loaded: ${result.data}")
                    onEvent(EditProductEvent.ProductFoundInDB(product = result.data.toUi()))
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка" //todo
                    logger.e(LOG_TAG, "Error loading product: $message")
                    sendEffect { EditProductEffect.ShowMessage("Ошибка загрузки продукта: $message") }
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Loading in progress...")
                }
            }
        }
    }

    private fun updateProductDB() {
        uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
            logger.d(LOG_TAG, "Start updating product: $product")

            viewModelScope.launch {
                when (val result = updateProductUseCase(product.toDomain())) {
                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "Product successfully updated")
                        setState { CommonUiState.Success(data = product) }
                        sendEffect { EditProductEffect.ShowMessage("Продукт успешно изменён") } //todo
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

    private companion object {
        const val LOG_TAG = "EditProductVM"
    }
}
