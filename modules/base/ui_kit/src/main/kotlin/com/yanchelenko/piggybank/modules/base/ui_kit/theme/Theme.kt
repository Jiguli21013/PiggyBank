package com.yanchelenko.piggynank.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppColors
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppShapes
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.AppTypography

@Composable
fun PiggyBankTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        useDarkTheme -> darkColorScheme(
            primary = AppColors.PrimaryDark,
            secondary = AppColors.SecondaryDark,
            background = AppColors.BackgroundDark,
            surface = AppColors.SurfaceDark,
            onPrimary = AppColors.TextPrimaryDark,
            onSurface = AppColors.TextPrimaryDark,
            error = AppColors.ErrorDark
        )
        else -> lightColorScheme(
            primary = AppColors.PrimaryLight,
            secondary = AppColors.SecondaryLight,
            background = AppColors.BackgroundLight,
            surface = AppColors.SurfaceLight,
            onPrimary = AppColors.TextPrimaryLight,
            onSurface = AppColors.TextPrimaryLight,
            error = AppColors.ErrorLight
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
