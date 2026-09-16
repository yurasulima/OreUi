package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import io.dangerous.oreui.LocalOreColors
import io.dangerous.oreui.OreDimens

@Composable
public fun OreMenuItem(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    enabled: Boolean = true,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    val colors = LocalOreColors.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surface, RectangleShape)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(OreDimens.MenuItemPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                title,
                color = if (enabled) colors.text else colors.textMuted
            )

            if (subtitle != null) {
                Spacer(Modifier.height(3.dp))
                Text(
                    subtitle,
                    color = colors.textDim,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )
            }
        }

        if (trailingContent != null) {
            Spacer(Modifier.width(12.dp))
            trailingContent()
        }
    }
}
