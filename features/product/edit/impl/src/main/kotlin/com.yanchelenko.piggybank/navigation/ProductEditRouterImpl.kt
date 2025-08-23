package com.yanchelenko.piggybank.navigation

import com.yanchelenko.piggybank.ProductEditRouter
import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductEditRouterImpl @Inject constructor(
    private val dispatcher: NavigationDispatcher
) : ProductEditRouter, BaseRouter(dispatcher)
