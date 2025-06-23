package com.yanchelenko.piggynank.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppColors {
    // Dark theme
    val PrimaryDark = Color(0xFF7A5CFA)
    val SecondaryDark = Color(0xFF3C3F4A)
    val BackgroundDark = Color(0xFF161C2D)
    val SurfaceDark = Color(0xFF1B1F36)
    val TextPrimaryDark = Color(0xFFFFFFFF)
    val TextSecondaryDark = Color(0xFFB0B0B0)
    val ErrorDark = Color(0xFFFF4C4C)

    // Light theme
    val PrimaryLight = Color(0xFF6848F1)
    val SecondaryLight = Color(0xFFF2F1FB)
    val BackgroundLight = Color(0xFFFFFFFF)
    val SurfaceLight = Color(0xFFF8F8FF)
    val TextPrimaryLight = Color(0xFF1C1C1E)
    val TextSecondaryLight = Color(0xFF5A5A5F)
    val ErrorLight = Color(0xFFD32F2F)
}

object CustomColors {
    val PrimaryTransparent10: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)

    val fsad: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)

    val SurfaceVariantSemiTransparent: Color
        @Composable
        get() = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
}
