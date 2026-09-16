package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dangerous.oreui.LocalOreColors

data class OreDropdownOption(val label: String, val value: String)

@Composable
fun OreDropdown(
    label: String,
    options: List<OreDropdownOption>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = LocalOreColors.current
    var expanded by remember { mutableStateOf(false) }
    val currentLabel = options.find { it.value == selectedValue }?.label ?: selectedValue

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                color = colors.text,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 2.dp),
            )
        }
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
                .background(colors.surfaceSunken)
                .border(3.dp, colors.outline, RectangleShape)
                .clickable(enabled = enabled) { expanded = !expanded },
            contentAlignment = Alignment.CenterStart,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentLabel,
                    color = if (enabled) colors.text else colors.textMuted,
                    fontSize = 12.sp,
                )
                
                // Down Arrow (Pixel/Minecraft Style Triangle)
                Box(
                    modifier = Modifier
                        .size(width = 10.dp, height = 6.dp)
                        .drawBehind {
                            val w = size.width
                            // Draw inverted triangle step-by-step
                            drawRect(color = colors.text, topLeft = Offset(0f, 0f), size = Size(w, 2.dp.toPx()))
                            drawRect(color = colors.text, topLeft = Offset(2.dp.toPx(), 2.dp.toPx()), size = Size(w - 4.dp.toPx(), 2.dp.toPx()))
                            drawRect(color = colors.text, topLeft = Offset(4.dp.toPx(), 4.dp.toPx()), size = Size(w - 8.dp.toPx(), 2.dp.toPx()))
                        }
                )
            }

            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    surface = colors.surfaceSunken,
                )
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(colors.surfaceSunken)
                        .border(3.dp, colors.outline, RectangleShape)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = option.label,
                                    color = colors.text,
                                    fontSize = 12.sp,
                                )
                            },
                            onClick = {
                                onValueChange(option.value)
                                expanded = false
                            },
                            modifier = Modifier
                                .background(if (option.value == selectedValue) colors.surface else Color.Transparent)
                        )
                    }
                }
            }
        }
    }
}
