package com.yanchelenko.piggybank.features.product_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.ui_models_android.mappers.toDomain
import com.yanchelenko.piggybank.common.ui_models_android.models.ProductUiModel
import com.yanchelenko.piggybank.common.ui_state.CommonUiState
import com.yanchelenko.piggybank.common.ui_state.getDataOrLog
import com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase
import com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect
import com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
import com.yanchelneko.piggybank.common.core_utils.Logger
import com.yanchelneko.piggybank.common.core_utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deleteProductUseCase: DeleteProductUseCase,
    logger: Logger
) : BaseViewModel<ProductDetailsEvent, CommonUiState<ProductUiModel>, ProductDetailsEffect>(
    initialState = CommonUiState.Initializing,
    logger = logger
) {

    init {
        val productJsonKey = AppDestination.ProductDetails.arguments.first().name
        logger.d(LOG_TAG, "Fetching product from SavedStateHandle with key: $productJsonKey")

        // как будто бы легче кидать idшник и заново запрашивать
        // а то тут чот страшное происходит
        val product: ProductUiModel = savedStateHandle.get<String>(productJsonKey)
            ?.let {
                try {
                    val json = URLDecoder.decode(it, StandardCharsets.UTF_8.name())
                    Json.decodeFromString<ProductUiModel>(json)
                } catch (e: Exception) {
                    logger.e(LOG_TAG, "Failed to parse product JSON: ${e.message}")
                    null
                }
            } ?: error("ProductUiModel not found or corrupted in SavedStateHandle") //todo error

        logger.d(LOG_TAG, "Parsed product: $product")

        setState { CommonUiState.Success(data = product) }
    }

    override fun onEvent(event: ProductDetailsEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")
        when (event) {
            is ProductDetailsEvent.OnEditClicked -> {
                uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
                    logger.d(LOG_TAG, "Edit requested for productId=${product.productId}")
                    sendEffect { ProductDetailsEffect.NavigateToEdit(productId = product.productId) }
                }
            }
            is ProductDetailsEvent.OnDeleteClicked -> {
                logger.d(LOG_TAG, "Delete dialog requested")
                sendEffect { ProductDetailsEffect.ShowDeleteDialog }
            }
            is ProductDetailsEvent.CancelDelete -> {
                logger.d(LOG_TAG, "Delete canceled")
                sendEffect { ProductDetailsEffect.CloseDeleteDialog }
            }
            is ProductDetailsEvent.ConfirmedDelete -> {
                uiState.value.getDataOrLog(LOG_TAG, logger) { product ->
                    logger.d(LOG_TAG, "Confirmed delete for productId=${product.productId}")
                    deleteProductFromDB()
                }
            }
        }
    }

    private fun deleteProductFromDB() {
        uiState.value.getDataOrLog(logTag = LOG_TAG, logger = logger) { product ->
            logger.d(LOG_TAG, "Attempting to delete product: $product")

            viewModelScope.launch {
                when (val result = deleteProductUseCase(product.toDomain())) {
                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "Product successfully deleted")
                        sendEffect { ProductDetailsEffect.NavigateBack }
                    }

                    is RequestResult.Error -> {
                        logger.e(LOG_TAG, "Error deleting product: ${result.error?.message}")
                        //todo
                    }

                    is RequestResult.InProgress -> {
                        logger.d(LOG_TAG, "Delete in progress")
                    }
                }
            }
        }
    }

    private companion object {
        const val LOG_TAG = "ProductDetailsVM"
    }
}
