package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.yanchelenko.piggybank.modules.base.ui_model.mappers.toUi
import com.yanchelenko.piggybank.modules.core.core_api.models.ProductOfCart
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.core.core_api.models.CartTotals
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartScreenState

fun PagingData<ProductOfCart>.toUiPagingData(): PagingData<ProductOfCartUiModel> = this.map { it.toUi() }

fun CartTotals.toCartScreenState(
    isCartClosed: Boolean = false
): CartScreenState = CartScreenState(
    itemsCount = itemsCount,
    totalWeightGrams = totalWeightGrams,
    totalPrice = totalPrice,
    isCartClosed = isCartClosed
)
