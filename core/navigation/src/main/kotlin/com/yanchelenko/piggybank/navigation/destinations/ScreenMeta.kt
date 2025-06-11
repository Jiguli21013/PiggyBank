package com.yanchelenko.piggybank.navigation.destinations

import androidx.annotation.StringRes

data class ScreenMeta(
    val routeTemplate: String,
    @StringRes val titleResId: Int
)
