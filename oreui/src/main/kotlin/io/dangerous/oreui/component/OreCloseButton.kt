package io.dangerous.oreui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dangerous.reblink.oreui.R
import io.dangerous.oreui.LocalOreColors

@Composable
fun OreCloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val colors = LocalOreColors.current

    // Flat Gray Colors matching Minecraft / Amethyst gray component style
    val baseBg = when {
        !enabled -> Color(0xFF3A3B3C)
        isPressed -> Color(0xFF313233)      // surfaceSunken dark gray
        isHovered -> colors.surfaceHover   // surfaceHover gray
        else -> colors.surface             // surface classic base gray
    }

    Box(
        modifier = modifier
            .size(32.dp) // Symmetrical clean sizing
            .background(baseBg)
            .border(3.dp, colors.outline, RectangleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.close_icon),
            contentDescription = "Close",
            modifier = Modifier.size(18.dp), // Increased size from 12.dp to 18.dp for clear readability
        )
    }
}
