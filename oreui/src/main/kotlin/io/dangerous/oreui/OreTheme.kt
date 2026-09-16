package io.dangerous.oreui

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

public enum class OreThemeVariant {
    Dark,
    Light
}

private fun OreColorScheme.toMaterial(isDark: Boolean): ColorScheme {
    val base = if (isDark) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = confirm,
        onPrimary = text,
        primaryContainer = confirmDark,
        onPrimaryContainer = text,
        inversePrimary = confirmLight,

        secondary = border,
        onSecondary = text,
        secondaryContainer = surface,
        onSecondaryContainer = text,

        background = background,
        onBackground = text,
        surface = surface,
        onSurface = text,
        surfaceVariant = surfaceSunken,
        onSurfaceVariant = textDim,

        outline = outline,
        outlineVariant = outline,

        error = danger,
        onError = text,
        errorContainer = danger,
        onErrorContainer = text,

        inverseSurface = text,
        inverseOnSurface = background,

        scrim = scrim
    )
}

private fun OreTypographyScheme.toMaterial(): androidx.compose.material3.Typography = androidx.compose.material3.Typography(
    bodyLarge = bodyLarge,
    bodyMedium = bodyMedium,
    bodySmall = bodySmall,
    titleLarge = titleLarge,
    titleMedium = titleMedium,
    titleSmall = titleSmall,
    labelLarge = labelLarge,
    labelMedium = labelMedium
)

@Composable
public fun OreTheme(
    variant: OreThemeVariant = OreThemeVariant.Dark,
    content: @Composable () -> Unit
) {
    val colors = OreColors.Amethyst // Forcing Amethyst as per CSS
    
    CompositionLocalProvider(
        LocalOreColors provides colors,
        LocalOreTypography provides OreTypography.Default
    ) {
        MaterialTheme(
            colorScheme = colors.toMaterial(variant == OreThemeVariant.Dark),
            typography = OreTypography.Default.toMaterial(),
            shapes = OreShapes.material,
            content = content
        )
    }
}
