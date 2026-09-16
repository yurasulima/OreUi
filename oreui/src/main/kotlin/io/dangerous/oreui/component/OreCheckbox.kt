package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import io.dangerous.oreui.LocalOreColors
import io.dangerous.oreui.OreDimens

@Composable
fun OreCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = LocalOreColors.current

    Box(
        modifier = modifier
            .size(36.dp)
            .clickable(
                enabled = enabled,
            ) {
                onCheckedChange(!checked)
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(
                    color = if (checked) {
                        colors.confirm
                    } else {
                        colors.surfaceSunken
                    }
                )
                .border(
                    width = OreDimens.BorderWidth,
                    color = colors.outline,
                    shape = RectangleShape,
                )
                .drawBehind {
                    if (!checked) return@drawBehind

                    val px = size.width / 10f
                    val color = colors.text

                    drawRect(
                        color = color,
                        topLeft = Offset(2 * px, 2 * px),
                        size = Size(2 * px, 2 * px),
                    )

                    drawRect(
                        color = color,
                        topLeft = Offset(4 * px, 4 * px),
                        size = Size(2 * px, 2 * px),
                    )

                    drawRect(
                        color = color,
                        topLeft = Offset(6 * px, 6 * px),
                        size = Size(2 * px, 2 * px),
                    )

                    drawRect(
                        color = color,
                        topLeft = Offset(6 * px, 2 * px),
                        size = Size(2 * px, 2 * px),
                    )

                    drawRect(
                        color = color,
                        topLeft = Offset(2 * px, 6 * px),
                        size = Size(2 * px, 2 * px),
                    )
                },
        )
    }
}