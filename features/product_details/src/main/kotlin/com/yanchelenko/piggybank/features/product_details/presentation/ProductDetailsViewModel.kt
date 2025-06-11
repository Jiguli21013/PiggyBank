package com.yanchelenko.piggybank.features.product_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.piggybank.common.mappers.toDomain
import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase
import com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect
import com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent
import com.yanchelenko.piggybank.navigation.destinations.AppDestination
import com.yanchelenko.piggynank.core.ui.base.BaseViewModel
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
    private val deleteProductUseCase: DeleteProductUseCase
) : BaseViewModel<ProductDetailsEvent, ProductUiModel, ProductDetailsEffect>(
    initialState = ProductUiModel(barcode = "")
) {

    init {
        val productJsonKey = AppDestination.ProductDetails.arguments.first().name
        val product: ProductUiModel = savedStateHandle.get<String>(productJsonKey)
            ?.let {
                try {
                    val json = URLDecoder.decode(it, StandardCharsets.UTF_8.name())
                    Json.decodeFromString<ProductUiModel>(json)
                } catch (e: Exception) {
                    // логирование, крашлитикс и т.п.
                    null
                }
            } ?: error("ProductUiModel not found or corrupted in SavedStateHandle")

        setState {
            copy(
                productId = product.productId,
                barcode = product.barcode,
                productName = product.productName,
                weight = product.weight,
                price = product.price,
                pricePerKg = product.pricePerKg,
                addedAt = product.addedAt
            )
        }
    }

    override fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.OnEditClicked -> {
                sendEffect { ProductDetailsEffect.NavigateToEdit(productId = state.productId) }
            }

            is ProductDetailsEvent.OnDeleteClicked -> {
                sendEffect { ProductDetailsEffect.ShowDeleteDialog }
            }

            is ProductDetailsEvent.CancelDelete -> {
                sendEffect { ProductDetailsEffect.CloseDeleteDialog }
            }

            is ProductDetailsEvent.ConfirmedDelete -> {
                deleteProductFromDB()
            }

        }
    }

    private fun deleteProductFromDB() {
        val current = uiState.value
        viewModelScope.launch {
            when (deleteProductUseCase.invoke(product = current.toDomain())) {
                is RequestResult.Success -> {
                    sendEffect { ProductDetailsEffect.NavigateBack }
                }

                is RequestResult.Error -> {

                }

                is RequestResult.InProgress -> Unit
            }
        }
    }
}
