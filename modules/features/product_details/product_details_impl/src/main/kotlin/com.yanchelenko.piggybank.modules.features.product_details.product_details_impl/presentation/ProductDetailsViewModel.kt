package com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsEffect
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsEvent
import com.yanchelenko.piggybank.modules.features.product_details.product_details_impl.presentation.state.ProductDetailsState
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.BaseViewModel
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.CommonUiState
import com.yanchelenko.piggybank.modules.base.infrastructure.mvi.getData
import com.yanchelenko.piggybank.modules.base.infrastructure.result.RequestResult
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toDomain
import com.yanchelenko.piggybank.modules.base.ui_model.models.ScannedProductUiModel
import com.yanchelenko.piggybank.modules.base.ui_model.models.StableInstant
import com.yanchelenko.piggybank.modules.core.core_api.domain.DeleteScannedProductUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductWithCurrentVersionByIdUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.GetProductVersionHistoryUseCase
import com.yanchelenko.piggybank.modules.core.core_api.exceptions.BaseDomainException
import com.yanchelenko.piggybank.modules.core.core_api.debugTools.Logger
import com.yanchelenko.piggybank.modules.core.core_api.domain.ObserveCurrencyUseCase
import com.yanchelenko.piggybank.modules.core.core_api.domain.mapper.toUserMessage
import com.yanchelenko.piggybank.modules.core.core_api.models.AppCurrency
import com.yanchelenko.piggybank.modules.core.core_api.models.GetProductWithCurrentVersionByIdResult
import com.yanchelenko.piggybank.modules.core.core_api.navigation.destinations.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val observeCurrencyUseCase: ObserveCurrencyUseCase,
    private val getProductWithCurrentVersionByIdUseCase: GetProductWithCurrentVersionByIdUseCase,
    private val getProductVersionHistoryUseCase: GetProductVersionHistoryUseCase,
    private val deleteScannedProductUseCase: DeleteScannedProductUseCase,
    private val logger: Logger,
) : BaseViewModel<ProductDetailsEvent, CommonUiState<ProductDetailsState>, ProductDetailsEffect>(
    initialState = CommonUiState.Initializing,
) {
    // Cache the productId for later refreshes
    private val productId: Long = savedStateHandle.get<Long>(AppDestination.ProductDetailsDestination.Meta.arguments.first().name)
        ?: error("productId is missing in SavedStateHandle")

    init {
        logger.d(LOG_TAG, "Init with productId=$productId")
        onEvent(event = ProductDetailsEvent.LoadProductByProductId(productId = productId))
    }

    /**
     * Explicit refresh API for the screen: call this from the UI when the screen returns to foreground.
     * This guarantees fresh data even if this VM was retained on the back stack.
     */
    fun refresh() {
        logger.d(LOG_TAG, "Manual refresh for productId=$productId")
        onEvent(ProductDetailsEvent.LoadProductByProductId(productId = productId))
    }

    override fun onEvent(event: ProductDetailsEvent) {
        logger.d(LOG_TAG, "Event received: ${event::class.simpleName}")
        when (event) {
            is ProductDetailsEvent.OnEditClicked -> {
                uiState.value.getData { state ->
                    logger.d(LOG_TAG, "Edit requested for productId=${state.product.productId}")
                    sendEffect { ProductDetailsEffect.NavigateToEdit(productId = state.product.productId) }
                }
            }
            is ProductDetailsEvent.OnDeleteClicked -> {
                logger.d(LOG_TAG, "Delete dialog requested")
                sendEffect { ProductDetailsEffect.ShowDeleteDialog }
            }
            is ProductDetailsEvent.DialogCancelDelete -> {
                logger.d(LOG_TAG, "Delete canceled")
                sendEffect { ProductDetailsEffect.CloseDeleteDialog }
            }
            is ProductDetailsEvent.DialogConfirmedDelete -> {
                uiState.value.getData { state ->
                    logger.d(LOG_TAG, "Confirmed delete for productId=${state.product.productId}")
                    sendEffect { ProductDetailsEffect.CloseDeleteDialog }
                    deleteProductFromDB()
                }
            }
            is ProductDetailsEvent.LoadProductByProductId -> {
                logger.d(LOG_TAG, "Loading product by ID: ${event.productId}")
                loadProductByProductId(productId = event.productId)
            }

            is ProductDetailsEvent.ProductFoundInDB -> {
                logger.d(LOG_TAG, "ScannedProduct loaded from DB: ${event.state}")
                setState { CommonUiState.Success(data = event.state) }
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

                            val historyResult = getProductVersionHistoryUseCase(productId = product.id)
                            val history = when (historyResult) {
                                is RequestResult.Success -> historyResult.data
                                is RequestResult.Error -> {
                                    logger.e(LOG_TAG, "Error loading version history: ${historyResult.error?.message}")
                                    emptyList()
                                }
                                is RequestResult.InProgress -> emptyList()
                            }

                            val previousVersion = history
                                .filter { it.id != currentVersion.id }
                                .maxByOrNull { it.createdAt.toEpochMilliseconds() }

                            onEvent(
                                ProductDetailsEvent.ProductFoundInDB(
                                    ProductDetailsState(
                                        product = ScannedProductUiModel(
                                            productId = product.id,
                                            barcode = product.barcode,
                                            productName = product.productName,
                                            weight = currentVersion.weightGrams,
                                            price = currentVersion.price,
                                            pricePerKg = currentVersion.pricePerKg,
                                            formattedPrice = currentVersion.price.toCurrencyText(currency = currentCurrency),
                                            formattedPricePerKg = currentVersion.pricePerKg.toCurrencyText(currency = currentCurrency),
                                            addedAt = currentVersion.createdAt.toStable(),
                                        ),
                                        previousPrice = previousVersion?.price,
                                        previousWeight = previousVersion?.weightGrams,
                                        hasPriceChanged = previousVersion?.price != null && previousVersion.price != currentVersion.price,
                                        hasWeightChanged = previousVersion?.weightGrams != null && previousVersion.weightGrams != currentVersion.weightGrams,
                                    )
                                )
                            )
                        }

                        is GetProductWithCurrentVersionByIdResult.NotFound,
                        is GetProductWithCurrentVersionByIdResult.ProductWithoutCurrentVersion -> {
                            val message = "Продукт не найден" //todo move to resources
                            logger.e(LOG_TAG, "Error loading product: $message")
                            //sendEffect { ProductDetailsEffect.ShowMessage(message) }
                        }
                    }
                }

                is RequestResult.Error -> {
                    val message = (result.error as? BaseDomainException)?.toUserMessage()
                        ?: result.error?.message
                        ?: "Неизвестная ошибка" //todo
                    logger.e(LOG_TAG, "Error loading product: $message")
                    //sendEffect { ProductDetailsEffect.ShowMessage("Ошибка загрузки продукта: $message") }//todo to res
                }

                is RequestResult.InProgress -> {
                    logger.d(LOG_TAG, "Loading in progress...")
                }
            }
        }
    }

    private fun deleteProductFromDB() {
        uiState.value.getData { state ->
            logger.d(LOG_TAG, "Attempting to delete product: ${state.product}")

            viewModelScope.launch {
                when (val result = deleteScannedProductUseCase(state.product.toDomain())) {

                    is RequestResult.Success -> {
                        logger.d(LOG_TAG, "ScannedProduct successfully deleted")
                        sendEffect { ProductDetailsEffect.DeletionAnimation }
                        logger.d(LOG_TAG, "Animation for deletion")
                        delay(timeMillis = DELETION_ANIMATION_DELAY_MS)
                        sendEffect { ProductDetailsEffect.NavigateBack }
                        logger.d(LOG_TAG, "Navigation back")
                    }

                    is RequestResult.Error -> {
                        logger.e(LOG_TAG, "Error deleting product: ${result.error?.message}")

                        //todo move to resources
                        val message = if (result.error?.message == PRODUCT_IN_ACTIVE_CART_ERROR_MESSAGE) {
                            "Нельзя удалить товар, пока он находится в корзине"
                        } else {
                            "Ошибка при удалении"
                        }

                        //sendEffect { ProductDetailsEffect.ShowMessage(message) }
                    }

                    is RequestResult.InProgress -> {
                        logger.d(LOG_TAG, "Delete in progress")
                    }
                }
            }
        }
    }

    private fun Double.toCurrencyText(currency: AppCurrency): String {
        return "$this ${currency.symbol}"
    }

    private fun kotlinx.datetime.Instant.toStable(): StableInstant {
        return StableInstant(epochMillis = toEpochMilliseconds())
    }

    private companion object {
        const val LOG_TAG = "ProductDetailsVM"
        const val PRODUCT_IN_ACTIVE_CART_ERROR_MESSAGE = "Scanned product is in active cart"
        const val DELETION_ANIMATION_DELAY_MS = 200L
    }
}
