package io.dangerous.oreui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.dangerous.oreui.LocalOreColors

@Composable
fun OreSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val colors = LocalOreColors.current
    
    // Base colors from CSS
    val outlineColor = colors.outline
    val confirmBg = colors.confirm
    val confirmLight = colors.confirmLight
    val offBg = Color(0xFF8C8D90)
    val offLight = Color(0xFFA3A4A6)
    
    val thumbBg = if (isHovered && enabled) Color(0xFFB1B2B5) else Color(0xFFD0D1D4)
    val thumbTopBorder = Color(0xFFECEDEE)
    val thumbSideBg = colors.border

    Box(
        modifier = modifier
            .size(width = 64.dp, height = 36.dp) // Larger hit area
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
            ) { 
                onCheckedChange(!checked) 
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 56.dp, height = 26.dp)
                .drawBehind {
                    // 1. Draw parent background as outlineColor (forms outer border)
                    drawRect(color = outlineColor)
                    
                    val x3 = 3.dp.toPx()
                    val x6 = 6.dp.toPx()
                    val x25 = 25.dp.toPx()
                    val x28 = 28.dp.toPx()
                    val x31 = 31.dp.toPx()
                    val x50 = 50.dp.toPx()
                    val x53 = 53.dp.toPx()
                    
                    val y3 = 3.dp.toPx()
                    val y6 = 6.dp.toPx()
                    val y20 = 20.dp.toPx()
                    val y23 = 23.dp.toPx()
                    
                    // toggle_base_on border area
                    drawRect(
                        color = confirmLight,
                        topLeft = Offset(x3, y3),
                        size = Size(x28 - x3, y23 - y3)
                    )
                    // toggle_base_on inner background
                    drawRect(
                        color = confirmBg,
                        topLeft = Offset(x6, y6),
                        size = Size(x25 - x6, y20 - y6)
                    )
                    // toggle_base_on right border (outlineColor)
                    drawRect(
                        color = outlineColor,
                        topLeft = Offset(x25, y3),
                        size = Size(x28 - x25, y23 - y3)
                    )
                    
                    // toggle_base_off border area
                    drawRect(
                        color = offLight,
                        topLeft = Offset(x28, y3),
                        size = Size(x53 - x28, y23 - y3)
                    )
                    // toggle_base_off inner background
                    drawRect(
                        color = offBg,
                        topLeft = Offset(x31, y6),
                        size = Size(x50 - x31, y20 - y6)
                    )
                    // toggle_base_off left border (outlineColor)
                    drawRect(
                        color = outlineColor,
                        topLeft = Offset(x28, y3),
                        size = Size(x31 - x28, y23 - y3)
                    )
                }
        ) {
            // Thumb (3D Layered)
            Box(
                modifier = Modifier
                    .offset(x = if (checked) 25.dp else 0.dp, y = (-5).dp)
                    .size(width = 31.dp, height = 31.dp)
                    .drawBehind {
                        // 1. Outer thumb border
                        drawRect(color = outlineColor)
                        
                        val tx3 = 3.dp.toPx()
                        val tx6 = 6.dp.toPx()
                        val tx25 = 25.dp.toPx()
                        val tx28 = 28.dp.toPx()
                        
                        val ty3 = 3.dp.toPx()
                        val ty6 = 6.dp.toPx()
                        val ty20 = 20.dp.toPx()
                        val ty23 = 23.dp.toPx()
                        val ty28 = 28.dp.toPx()
                        
                        // 2. toggle_top border area (Color: thumbTopBorder)
                        drawRect(
                            color = thumbTopBorder,
                            topLeft = Offset(tx3, ty3),
                            size = Size(tx28 - tx3, ty23 - ty3)
                        )
                        // toggle_top inner background (Color: thumbBg)
                        drawRect(
                            color = thumbBg,
                            topLeft = Offset(tx6, ty6),
                            size = Size(tx25 - tx6, ty20 - ty6)
                        )
                        
                        // 3. toggle_side (Color: thumbSideBg)
                        drawRect(
                            color = thumbSideBg,
                            topLeft = Offset(tx3, ty23),
                            size = Size(tx28 - tx3, ty28 - ty23)
                        )
                    }
            )
        }
    }
}
