package com.yanchelenko.piggybank.modules.base.ui_kit.theme

import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppTheme

@Composable
fun PiggyBankTheme(
    appTheme: AppTheme = AppTheme.SYSTEM,
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val useDarkTheme = when (appTheme) {
        AppTheme.SYSTEM -> isSystemInDarkTheme()
        AppTheme.DARK -> true
        AppTheme.LIGHT -> false
    }

    val targetColorScheme = when {
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
            onPrimary = AppColors.TextPrimaryDark,
            onSurface = AppColors.TextPrimaryLight,
            error = AppColors.ErrorLight
        )
    }

    val colorScheme = animatedColorScheme(targetColorScheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}

@Composable
private fun animatedColorScheme(
    target: ColorScheme,
): ColorScheme {
    val durationMillis = 400

    val primary by animateColorAsState(
        targetValue = target.primary,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_primary",
    )
    val onPrimary by animateColorAsState(
        targetValue = target.onPrimary,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onPrimary",
    )
    val primaryContainer by animateColorAsState(
        targetValue = target.primaryContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_primaryContainer",
    )
    val onPrimaryContainer by animateColorAsState(
        targetValue = target.onPrimaryContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onPrimaryContainer",
    )
    val inversePrimary by animateColorAsState(
        targetValue = target.inversePrimary,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_inversePrimary",
    )
    val secondary by animateColorAsState(
        targetValue = target.secondary,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_secondary",
    )
    val onSecondary by animateColorAsState(
        targetValue = target.onSecondary,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onSecondary",
    )
    val secondaryContainer by animateColorAsState(
        targetValue = target.secondaryContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_secondaryContainer",
    )
    val onSecondaryContainer by animateColorAsState(
        targetValue = target.onSecondaryContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onSecondaryContainer",
    )
    val tertiary by animateColorAsState(
        targetValue = target.tertiary,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_tertiary",
    )
    val onTertiary by animateColorAsState(
        targetValue = target.onTertiary,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onTertiary",
    )
    val tertiaryContainer by animateColorAsState(
        targetValue = target.tertiaryContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_tertiaryContainer",
    )
    val onTertiaryContainer by animateColorAsState(
        targetValue = target.onTertiaryContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onTertiaryContainer",
    )
    val background by animateColorAsState(
        targetValue = target.background,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_background",
    )
    val onBackground by animateColorAsState(
        targetValue = target.onBackground,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onBackground",
    )
    val surface by animateColorAsState(
        targetValue = target.surface,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_surface",
    )
    val onSurface by animateColorAsState(
        targetValue = target.onSurface,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onSurface",
    )
    val surfaceVariant by animateColorAsState(
        targetValue = target.surfaceVariant,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_surfaceVariant",
    )
    val onSurfaceVariant by animateColorAsState(
        targetValue = target.onSurfaceVariant,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onSurfaceVariant",
    )
    val surfaceTint by animateColorAsState(
        targetValue = target.surfaceTint,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_surfaceTint",
    )
    val inverseSurface by animateColorAsState(
        targetValue = target.inverseSurface,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_inverseSurface",
    )
    val inverseOnSurface by animateColorAsState(
        targetValue = target.inverseOnSurface,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_inverseOnSurface",
    )
    val error by animateColorAsState(
        targetValue = target.error,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_error",
    )
    val onError by animateColorAsState(
        targetValue = target.onError,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onError",
    )
    val errorContainer by animateColorAsState(
        targetValue = target.errorContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_errorContainer",
    )
    val onErrorContainer by animateColorAsState(
        targetValue = target.onErrorContainer,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_onErrorContainer",
    )
    val outline by animateColorAsState(
        targetValue = target.outline,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_outline",
    )
    val outlineVariant by animateColorAsState(
        targetValue = target.outlineVariant,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_outlineVariant",
    )
    val scrim by animateColorAsState(
        targetValue = target.scrim,
        animationSpec = tween(durationMillis = durationMillis),
        label = "theme_scrim",
    )

    return target.copy(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = inversePrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surfaceTint,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
    )
}
