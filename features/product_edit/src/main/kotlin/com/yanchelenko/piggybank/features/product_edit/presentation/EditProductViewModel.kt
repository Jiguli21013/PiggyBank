package com.yanchelenko.piggybank.features.product_edit.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toUi
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
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
) : BaseViewModel<EditProductEvent, EditProductUiState, EditProductEffect>(
    logger = logger,
    initialState = EditProductUiState(
        uiProduct = ProductUiModel(
            barcode = "",
            productId = savedStateHandle.get<Long>(AppDestination.EditProduct.arguments.first().name) ?: 0L
        )
    )
) {

    init {
        logger.d(LOG_TAG, "Init with productId=${state.uiProduct.productId}")
        state.uiProduct.productId.let {
            onEvent(event = EditProductEvent.LoadProductByProductId(productId = it))
        }
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
                    updateUiProduct {
                        copy(productName = event.name)
                    }
                }
            }

            is EditProductEvent.WeightChanged -> {
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

            is EditProductEvent.PriceChanged -> {
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
                setState { copy(uiProduct = event.product) }
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
                        ?: "Неизвестная ошибка"
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
        logger.d(LOG_TAG, "Start updating product: ${state.uiProduct}")
        viewModelScope.launch {
            setState { copy(isSaving = true, errorMessage = null) }

            when (val result = updateProductUseCase(product = state.toDomain())) {
                is RequestResult.Success -> {
                    logger.d(LOG_TAG, "Product successfully updated")
                    setState { copy(isSaving = false, isSaved = true) }
                    sendEffect { EditProductEffect.ShowMessage("Продукт успешно изменён") }
                    sendEffect { EditProductEffect.NavigateBack }
                }

                is RequestResult.Error -> {
                    val message = result.error?.message ?: "Неизвестная ошибка" //todo вынести
                    logger.e(LOG_TAG, "Error updating product: $message")
                    setState { copy(isSaving = false, errorMessage = message) }
                    sendEffect { EditProductEffect.ShowMessage("Ошибка при изменении: $message") }
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Update in progress...")
                }
            }
        }
    }

    private companion object {
        const val LOG_TAG = "EditProductVM"
    }
}
