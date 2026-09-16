package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dangerous.oreui.LocalOreColors
import kotlin.math.abs

@Composable
fun OreSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: List<Float> = emptyList(),
    label: String = "",
    enabled: Boolean = true,
) {
    val colors = LocalOreColors.current
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val outlineColor = colors.outline
    val thumbBg = if (isHovered && enabled) Color(0xFFB1B2B5) else Color(0xFFD0D1D4)
    val thumbTopBorder = Color(0xFFECEDEE)
    val thumbSideBg = colors.border

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                color = colors.text,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 2.dp),
            )
        }

        var dragX by remember { mutableStateOf(0f) }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .pointerInput(enabled, valueRange, steps) {
                    if (!enabled) return@pointerInput
                    val widthPx = size.width.toFloat()
                    val rangeSize = valueRange.endInclusive - valueRange.start

                    fun updateValueFromX(x: Float) {
                        val f = (x / widthPx).coerceIn(0f, 1f)
                        val raw = valueRange.start + (f * rangeSize)
                        val snapped =
                            if (steps.isEmpty()) raw else steps.minByOrNull { abs(it - raw) } ?: raw
                        onValueChange(snapped)
                    }

                    detectDragGestures(
                        onDragStart = { offset ->
                            dragX = offset.x
                            updateValueFromX(dragX)
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            dragX += dragAmount.x
                            updateValueFromX(dragX)
                        }
                    )
                }
                .pointerInput(enabled, valueRange, steps) {
                    if (!enabled) return@pointerInput
                    val widthPx = size.width.toFloat()
                    val rangeSize = valueRange.endInclusive - valueRange.start

                    fun updateValueFromX(x: Float) {
                        val f = (x / widthPx).coerceIn(0f, 1f)
                        val raw = valueRange.start + (f * rangeSize)
                        val snapped =
                            if (steps.isEmpty()) raw else steps.minByOrNull { abs(it - raw) } ?: raw
                        onValueChange(snapped)
                    }

                    detectTapGestures(
                        onTap = { offset -> updateValueFromX(offset.x) }
                    )
                },
            contentAlignment = Alignment.CenterStart
        ) {
            val widthPx = constraints.maxWidth.toFloat()
            val rangeSize = valueRange.endInclusive - valueRange.start
            val density = LocalDensity.current
            val thumbWidthDp = 20.dp
            val thumbWidthPx = with(density) { thumbWidthDp.toPx() }
            val maxOffsetPx = (widthPx - thumbWidthPx).coerceAtLeast(0f)

            val fraction = ((value - valueRange.start) / rangeSize).coerceIn(0f, 1f)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(colors.surfaceSunken)
                    .border(2.dp, colors.outline, RectangleShape)
                    .drawBehind {
                        if (steps.isNotEmpty()) {
                            steps.forEach { step ->
                                val stepFraction = (step - valueRange.start) / rangeSize
                                val xPos = stepFraction * size.width
                                drawRect(
                                    color = colors.outline,
                                    topLeft = Offset(xPos - 1.5.dp.toPx(), 0f),
                                    size = Size(3.dp.toPx(), size.height)
                                )
                            }
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = fraction)
                        .fillMaxHeight()
                        .background(colors.confirm.copy(alpha = if (enabled) 0.6f else 0.3f)),
                )
            }

            val thumbOffsetPx = fraction * maxOffsetPx

            Box(
                modifier = Modifier
                    .offset(x = with(density) { thumbOffsetPx.toDp() }, y = (-2).dp)
                    .size(width = thumbWidthDp, height = 24.dp)
                    .hoverable(interactionSource = interactionSource, enabled = enabled)
                    .drawBehind {
                        drawRect(color = outlineColor)
                        val tx2 = 2.dp.toPx(); val tx4 = 4.dp.toPx(); val tx16 = 16.dp.toPx(); val tx18 = 18.dp.toPx()
                        val ty2 = 2.dp.toPx(); val ty4 = 4.dp.toPx(); val ty16 = 16.dp.toPx(); val ty18 = 18.dp.toPx(); val ty22 = 22.dp.toPx()
                        drawRect(color = thumbTopBorder, topLeft = Offset(tx2, ty2), size = Size(tx18 - tx2, ty18 - ty2))
                        drawRect(color = thumbBg, topLeft = Offset(tx4, ty4), size = Size(tx16 - tx4, ty16 - ty4))
                        drawRect(color = thumbSideBg, topLeft = Offset(tx2, ty18), size = Size(tx18 - tx2, ty22 - ty18))
                    }
            )
        }
    }
}
