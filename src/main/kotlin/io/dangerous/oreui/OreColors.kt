package io.dangerous.oreui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

public data class OreColorScheme(
    val outline: Color,
    val surfaceSunken: Color,
    val surface: Color,
    val surfaceHover: Color,
    val surfaceHoverStrong: Color,
    val border: Color,
    val text: Color,
    val textDim: Color,
    val textMuted: Color,
    
    val confirm: Color,
    val confirmLight: Color,
    val confirmMid: Color,
    val confirmDark: Color,
    val confirmBorder: Color,

    val danger: Color,
    val dangerLight: Color,
    val dangerMid: Color,
    val dangerDark: Color,
    val dangerBorder: Color,
    val dangerText: Color,

    val scrim: Color,
    val background: Color, // Fallback/Global background
    
    // For legacy compatibility or derived usage
    val textDisabled: Color = Color(0xFF58585a),
    val borderStrong: Color = Color(0xFF1e1e1f)
)

public object OreColors {
    public val Amethyst: OreColorScheme = OreColorScheme(
        outline = Color(0xFF1E1E1F),
        surfaceSunken = Color(0xFF313233),
        surface = Color(0xFF48494A),
        surfaceHover = Color(0xFF5A5B5C),
        surfaceHoverStrong = Color(0xFF6E6F70),
        border = Color(0xFF58585A),
        text = Color(0xFFFFFFFF),
        textDim = Color(0xFFE0E0E0),
        textMuted = Color(0xFF9F9F9F),
        
        confirm = Color(0xFF3C8527),
        confirmLight = Color(0xFF639D52),
        confirmMid = Color(0xFF4F913C),
        confirmDark = Color(0xFF2A641C),
        confirmBorder = Color(0xFF1D4D13),

        danger = Color(0xFFCA3636),
        dangerLight = Color(0xFFD55E5E),
        dangerMid = Color(0xFFCF4A4A),
        dangerDark = Color(0xFFC02D2D),
        dangerBorder = Color(0xFFAD1D1D),
        dangerText = Color(0xFFF87171),

        scrim = Color(0xBB000000),
        background = Color(0xFF313233)
    )

    // Keeping Dark as a reference or primary theme
    public val Dark: OreColorScheme = Amethyst
}

public val LocalOreColors = staticCompositionLocalOf { OreColors.Amethyst }
