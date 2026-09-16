package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dangerous.oreui.LocalOreColors

enum class OreButtonVariant {
    Confirm,
    Danger,
    Gray
}

@Composable
fun OreButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: OreButtonVariant = OreButtonVariant.Confirm,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val colors = LocalOreColors.current

    val palette = when (variant) {
        OreButtonVariant.Confirm -> ButtonPalette(
            containerBorder = colors.outline,
            shadowColor = colors.confirmBorder,
            baseBg = colors.confirm,
            topLeftBorder = colors.confirmLight,
            bottomRightBorder = colors.confirmMid,
            hoverBg = colors.confirmDark,
            textColor = colors.text,
        )
        OreButtonVariant.Danger -> ButtonPalette(
            containerBorder = colors.outline,
            shadowColor = colors.dangerBorder,
            baseBg = colors.danger,
            topLeftBorder = colors.dangerLight,
            bottomRightBorder = colors.dangerMid,
            hoverBg = colors.dangerDark,
            textColor = colors.text,
        )
        OreButtonVariant.Gray -> ButtonPalette(
            containerBorder = colors.outline,
            shadowColor = Color(0xFF333334),
            baseBg = colors.surface,
            topLeftBorder = colors.surfaceHover,
            bottomRightBorder = Color(0xFF3A3B3C),
            hoverBg = Color(0xFF3A3B3C),
            textColor = colors.text,
        )
    }

    val actualPalette = if (enabled) palette else ButtonPalette(
        containerBorder = colors.border,
        shadowColor = Color(0xFF8C8D90),
        baseBg = Color(0xFFB1B2B5),
        topLeftBorder = Color(0xFFB1B2B5),
        bottomRightBorder = Color(0xFFB1B2B5),
        hoverBg = Color(0xFFB1B2B5),
        textColor = colors.border,
    )

    val shadowSize = 4.dp

    Column(
        modifier = modifier
            .defaultMinSize(minHeight = 32.dp)
            .height(IntrinsicSize.Min)
            .pointerInput(enabled, onClick) {
                if (enabled) {
                    detectTapGestures(
                        onPress = { offset ->
                            val press = PressInteraction.Press(offset)
                            interactionSource.tryEmit(press)
                            val released = tryAwaitRelease()
                            if (released) {
                                interactionSource.tryEmit(PressInteraction.Release(press))
                                onClick()
                            } else {
                                interactionSource.tryEmit(PressInteraction.Cancel(press))
                            }
                        }
                    )
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .then(if (isPressed) Modifier.padding(top = shadowSize) else Modifier)
                .background(if (isHovered && enabled) actualPalette.hoverBg else actualPalette.baseBg)
                .border(2.dp, actualPalette.containerBorder, RectangleShape)
                .drawBehind {
                    val w = 2.dp.toPx()
                    drawRect(color = actualPalette.topLeftBorder, topLeft = Offset(w, w), size = Size(size.width - 2 * w, w))
                    drawRect(color = actualPalette.topLeftBorder, topLeft = Offset(w, w), size = Size(w, size.height - 2 * w))
                    drawRect(color = actualPalette.bottomRightBorder, topLeft = Offset(w, size.height - 2 * w), size = Size(size.width - 2 * w, w))
                    drawRect(color = actualPalette.bottomRightBorder, topLeft = Offset(size.width - w * 2, w), size = Size(w, size.height - 2 * w))
                }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = actualPalette.textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        }

        if (!isPressed && enabled) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(shadowSize)
                    .background(actualPalette.shadowColor)
                    .drawBehind {
                        val outline = actualPalette.containerBorder
                        val thickness = 2.dp.toPx()
                        drawRect(color = outline, topLeft = Offset(0f, size.height - thickness), size = Size(size.width, thickness))
                        drawRect(color = outline, topLeft = Offset(0f, 0f), size = Size(thickness, size.height))
                        drawRect(color = outline, topLeft = Offset(size.width - thickness, 0f), size = Size(thickness, size.height))
                    }
            )
        }
    }
}

private data class ButtonPalette(
    val containerBorder: Color,
    val shadowColor: Color,
    val baseBg: Color,
    val topLeftBorder: Color,
    val bottomRightBorder: Color,
    val hoverBg: Color,
    val textColor: Color,
)