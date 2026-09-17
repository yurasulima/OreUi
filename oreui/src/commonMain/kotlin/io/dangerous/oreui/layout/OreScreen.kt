package io.dangerous.oreui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dangerous.oreui.LocalOreColors
import io.dangerous.oreui.OreDimens
import io.dangerous.oreui.component.OreHeader

@Composable
public fun OreScreen(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = LocalOreColors.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .border(OreDimens.BorderWidth, colors.border)
        ) {
            OreHeader(
                title = title,
                onBack = onBack
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(OreDimens.ScreenPadding),
                verticalArrangement = Arrangement.spacedBy(OreDimens.SectionGap),
                content = content
            )
        }
    }
}
