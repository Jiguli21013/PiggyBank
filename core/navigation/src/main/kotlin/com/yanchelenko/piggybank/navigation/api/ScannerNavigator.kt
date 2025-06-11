package com.yanchelenko.piggybank.navigation.api

interface ScannerNavigator {
    fun navigateToInsertProduct(barcode: String)
    fun navigateBack()
}
