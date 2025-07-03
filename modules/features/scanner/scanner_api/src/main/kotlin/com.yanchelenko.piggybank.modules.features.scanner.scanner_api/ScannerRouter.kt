package com.yanchelenko.piggybank.modules.features.scanner.scanner_api

import com.yanchelenko.piggybank.modules.core.core_api.navigation.CommonRouter

interface ScannerRouter : CommonRouter {
    fun navigateToInsertProduct(barcode: String)
}
