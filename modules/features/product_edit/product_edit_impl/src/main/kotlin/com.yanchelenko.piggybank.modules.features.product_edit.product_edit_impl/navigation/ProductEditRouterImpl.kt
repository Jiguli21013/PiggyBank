package com.yanchelenko.piggybank.modules.features.product_edit.product_edit_impl.navigation

import com.yanchelenko.piggybank.modules.core.core_api.navigation.BaseRouter
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggybank.modules.features.product_edit.product_edit_api.ProductEditRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductEditRouterImpl @Inject constructor(
    private val dispatcher: NavigationDispatcher
) : ProductEditRouter, BaseRouter(dispatcher)
