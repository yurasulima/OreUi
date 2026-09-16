package io.dangerous.oreui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dangerous.oreui.LocalOreColors

@Composable
public fun OreHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val colors = LocalOreColors.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Black.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            OreButton(
                text = "<",
                onClick = onBack,
                variant = OreButtonVariant.Gray,
                modifier = Modifier.width(36.dp).height(36.dp)
            )
            Spacer(Modifier.width(12.dp))
        }

        Text(
            text = title.uppercase(),
            color = colors.text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Row(content = actions)
    }
}
