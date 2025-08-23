package com.yanchelenko.piggybank

import com.yanchelenko.piggybank.navigation.CommonRouter

interface ScannerRouter : CommonRouter {
    fun navigateToInsertProduct(barcode: String)
}
