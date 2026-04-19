package com.yanchelenko.piggybank.modules.base.ui_kit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppColors {
    // Dark theme
    val PrimaryDark = Color(0xFF3F6141)
    val SecondaryDark = Color(0xFF3C3F4A)
    val BackgroundDark = Color(0xFF161C2D)
    val SurfaceDark = Color(0xFF1B1F36)
    val TextPrimaryDark = Color(0xFFFFFFFF)
    val TextSecondaryDark = Color(0xFFB0B0B0)
    val ErrorDark = Color(0xFFFF4C4C)

    // Light theme
    val PrimaryLight = Color(0xFF7FC98F)
    val SecondaryLight = Color(0xFFBBDCF6)
    val BackgroundLight = Color(0xFFF6F9FC)
    val SurfaceLight = Color(0xFFDEE6EA)
    val TextPrimaryLight = Color(0xFF2E3A46)
    val TextSecondaryLight = Color(0xFF6F7E8C)
    val ErrorLight = Color(0xFFE35454)
}

object CustomColors {
    val AddToCart = Color(0xFFC8E6C9)      // мягкий зелёный
    val RemoveFromCart = Color(0xFFFFCDD2) // мягкий красный

    val PrimaryTransparent10: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)

    val SurfaceVariantSoft: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)

    val SurfaceVariantSemiTransparent: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
}
