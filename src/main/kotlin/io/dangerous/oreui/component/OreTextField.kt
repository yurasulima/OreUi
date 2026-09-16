package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dangerous.oreui.LocalOreColors

@Composable
fun OreTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
) {
    val colors = LocalOreColors.current
    Column(modifier = modifier.fillMaxWidth()) {
        if (label != null) {
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
                .height(36.dp)
                .background(colors.surfaceSunken)
                .border(3.dp, colors.outline, RectangleShape),
            contentAlignment = Alignment.CenterStart,
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), // Increased padding to 12.dp for better text clearance
                enabled = enabled,
                singleLine = singleLine,
                textStyle = TextStyle(color = colors.text, fontSize = 14.sp),
                cursorBrush = SolidColor(colors.text),
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (value.isEmpty() && placeholder != null) {
                            Text(
                                text = placeholder,
                                color = Color(0xFF6F6F6F),
                                fontSize = 14.sp,
                            )
                        }
                        innerTextField()
                    }
                },
            )
        }
    }
}
