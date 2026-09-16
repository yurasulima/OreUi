package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import io.dangerous.oreui.LocalOreColors
import io.dangerous.oreui.OreDimens

@Composable
public fun OreCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = LocalOreColors.current
    Box(
        modifier = modifier
            .background(colors.surfaceSunken)
            .border(3.dp, colors.outline, RectangleShape)
    ) {
        Column(
            modifier = Modifier.padding(OreDimens.CardPadding),
            content = content
        )
    }
}
