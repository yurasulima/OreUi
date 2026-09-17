package io.dangerous.oreui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import io.dangerous.oreui.generated.resources.Res
import io.dangerous.oreui.generated.resources.minecraftseven
import io.dangerous.oreui.generated.resources.minecraftten
import org.jetbrains.compose.resources.Font

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
    @Composable
    public fun default(): OreTypographyScheme {
        val minecraftSeven = FontFamily(Font(Res.font.minecraftseven))
        val minecraftTen = FontFamily(Font(Res.font.minecraftten))
        return OreTypographyScheme(
            bodyLarge   = TextStyle(fontFamily = minecraftSeven, fontSize = 16.sp, lineHeight = 22.sp),
            bodyMedium  = TextStyle(fontFamily = minecraftSeven, fontSize = 14.sp, lineHeight = 20.sp),
            bodySmall   = TextStyle(fontFamily = minecraftSeven, fontSize = 12.sp, lineHeight = 16.sp),
            titleLarge  = TextStyle(fontFamily = minecraftTen,   fontSize = 22.sp, lineHeight = 28.sp),
            titleMedium = TextStyle(fontFamily = minecraftTen,   fontSize = 17.sp, lineHeight = 22.sp),
            titleSmall  = TextStyle(fontFamily = minecraftTen,   fontSize = 15.sp, lineHeight = 20.sp),
            labelLarge  = TextStyle(fontFamily = minecraftTen,   fontSize = 15.sp, lineHeight = 20.sp),
            labelMedium = TextStyle(fontFamily = minecraftTen,   fontSize = 13.sp, lineHeight = 18.sp),
        )
    }
}

public val LocalOreTypography = staticCompositionLocalOf<OreTypographyScheme?> { null }
