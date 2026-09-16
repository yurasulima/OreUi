package io.dangerous.oreui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.dangerous.reblink.oreui.R

public data class OreTypographyScheme(
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle
)

public object OreTypography {
    private val MinecraftSeven = FontFamily(Font(R.font.minecraftseven))
    private val MinecraftTen = FontFamily(Font(R.font.minecraftten))

    public val Default: OreTypographyScheme = OreTypographyScheme(
        bodyLarge = TextStyle(
            fontFamily = MinecraftSeven,
            fontSize = 16.sp,
            lineHeight = 22.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = MinecraftSeven,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        bodySmall = TextStyle(
            fontFamily = MinecraftSeven,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        titleLarge = TextStyle(
            fontFamily = MinecraftTen,
            fontSize = 22.sp,
            lineHeight = 28.sp
        ),
        titleMedium = TextStyle(
            fontFamily = MinecraftTen,
            fontSize = 17.sp,
            lineHeight = 22.sp
        ),
        titleSmall = TextStyle(
            fontFamily = MinecraftTen,
            fontSize = 15.sp,
            lineHeight = 20.sp
        ),
        labelLarge = TextStyle(
            fontFamily = MinecraftTen,
            fontSize = 15.sp,
            lineHeight = 20.sp
        ),
        labelMedium = TextStyle(
            fontFamily = MinecraftTen,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    )
}

public val LocalOreTypography = staticCompositionLocalOf { OreTypography.Default }
