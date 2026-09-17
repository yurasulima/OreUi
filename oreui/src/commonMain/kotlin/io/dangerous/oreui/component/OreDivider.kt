package io.dangerous.oreui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dangerous.oreui.LocalOreColors

@Composable
public fun OreDivider(
    modifier: Modifier = Modifier
) {
    val colors = LocalOreColors.current
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colors.outline
    )
}
